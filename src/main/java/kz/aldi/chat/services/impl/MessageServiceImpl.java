package kz.aldi.chat.services.impl;

import kz.aldi.chat.models.dto.ChatRoomDto;
import kz.aldi.chat.models.dto.MessageDto;
import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.entity.Message;
import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.models.request.MessageRequest;
import kz.aldi.chat.repositories.MessageRepository;
import kz.aldi.chat.services.ChatRoomService;
import kz.aldi.chat.services.MessageService;
import kz.aldi.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    @Override
    public List<Message> sendMessageToUser(MessageRequest message) {
        var fromUserOpt = userService.findById(message.getFromUserId());
        var toUserOpt = userService.findById(message.getToUserId());
        var toChatRoom = Optional.empty();
        if (fromUserOpt.isPresent() && toUserOpt.isPresent()) {
            for (ChatRoom chatRoom : fromUserOpt.get().getChatRooms()) {
                for (User toUser : chatRoom.getUsers()) {
                    if (toUserOpt.get().getId().equals(toUser.getId())
                            && chatRoom.getUsers().size() == 2) {
                        toChatRoom = Optional.of(chatRoom);
                    }
                }
            }
            if (toChatRoom.isEmpty()) {
                ChatRoomDto chatRoomDto = new ChatRoomDto();
                chatRoomDto.setUsers(List.of(fromUserOpt.get(), toUserOpt.get()));
                chatRoomDto.setName(fromUserOpt.get().getName().concat("_").concat(toUserOpt.get().getName()));
                toChatRoom = Optional.of(chatRoomService.create(chatRoomDto));
            }
            MessageDto messageDto = new MessageDto();
            messageDto.setChatRoom((ChatRoom) toChatRoom.get());
            messageDto.setMessageContent(message.getText());
            messageDto.setSender(fromUserOpt.get());
            create(messageDto);
            return ((ChatRoom) toChatRoom.get()).getMessages();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Message> sendMessageToGroup(MessageRequest message) {
        var fromUserOpt = userService.findById(message.getFromUserId());
        if (fromUserOpt.isPresent()) {
            for (ChatRoom chatRoom : fromUserOpt.get().getChatRooms()) {
                if (chatRoom.getId().equals(message.getToChatId())) {
                    MessageDto messageDto = new MessageDto();
                    messageDto.setMessageContent(message.getText());
                    messageDto.setSender(fromUserOpt.get());
                    messageDto.setChatRoom(chatRoom);
                    create(messageDto);
                    return chatRoom.getMessages();
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Message create(MessageDto messageDto) {
        Message message = new Message();
        message.setMessageContent(messageDto.getMessageContent());
        message.setChatRoom(messageDto.getChatRoom());
        message.setSender(messageDto.getSender());
        return messageRepository.save(message);
    }
}
