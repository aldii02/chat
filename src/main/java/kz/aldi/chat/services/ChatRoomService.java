package kz.aldi.chat.services;

import kz.aldi.chat.models.dto.ChatRoomDto;
import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.request.ChatRoomRequest;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    ChatRoom create(ChatRoomDto chatRoomDto);
    ChatRoom create(ChatRoomRequest request);
    List<ChatRoom> getChatRooms(Long userId);

    Optional<ChatRoom> findById(Long id);
}
