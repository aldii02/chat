package kz.aldi.chat.services.impl;

import kz.aldi.chat.models.dto.ChatRoomDto;
import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.entity.User;
import kz.aldi.chat.models.request.ChatRoomRequest;
import kz.aldi.chat.repositories.ChatRoomRepository;
import kz.aldi.chat.services.ChatRoomService;
import kz.aldi.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    @Override
    public ChatRoom create(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomDto.getName());
        chatRoom.setMessages(chatRoomDto.getMessages());
        chatRoom.setUsers(chatRoomDto.getUsers());
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public ChatRoom create(ChatRoomRequest request) {
        List<User> userList = new ArrayList<>();
        for(Long userId : request.getUserIdList()) {
            var userOpt = userService.findById(userId);
            if(userOpt.isPresent()) {
                userList.add(userOpt.get());
            }
        }
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setName(request.getName());
        chatRoomDto.setUsers(userList);
        return create(chatRoomDto);
    }

    @Override
    public List<ChatRoom> getChatRooms(Long userId) {
        var userOpt = userService.findById(userId);
        if(userOpt.isPresent()) {
            return userOpt.get().getChatRooms();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }
}
