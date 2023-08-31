package kz.aldi.chat.controllers;

import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.request.ChatRoomRequest;
import kz.aldi.chat.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatRooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoom> create(@RequestBody ChatRoomRequest request) {
        return ResponseEntity.ok(chatRoomService.create(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatRoom>> getChatRooms(@PathVariable Long userId) {
        return ResponseEntity.ok(chatRoomService.getChatRooms(userId));
    }
}
