package kz.aldi.chat.controllers;

import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.entity.Message;
import kz.aldi.chat.models.request.MessageRequest;
import kz.aldi.chat.services.ChatRoomService;
import kz.aldi.chat.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    private final ChatRoomService chatRoomService;

    @PostMapping("/send")
    public ResponseEntity<List<Message>> sendMessage(@RequestBody MessageRequest message) {
        if (message.getFromUserId() != null && message.getToChatId() != null && message.getText() != null) {
            return ResponseEntity.ok(messageService.sendMessageToGroup(message));
        } else if (message.getFromUserId() != null && message.getToUserId() != null && message.getText() != null) {
            return ResponseEntity.ok(messageService.sendMessageToUser(message));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("chatId") Long chatId) {
        var chatRoomOpt = chatRoomService.findById(chatId);
        if(chatRoomOpt.isPresent()) {
            return ResponseEntity.ok(chatRoomOpt.get().getMessages());
        }
        return ResponseEntity.ok(new ArrayList<>());
    }
}
