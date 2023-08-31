package kz.aldi.chat.models.dto;

import kz.aldi.chat.models.entity.ChatRoom;
import kz.aldi.chat.models.entity.User;
import lombok.Data;


@Data
public class MessageDto {
    private User sender;
    private ChatRoom chatRoom;
    private String messageContent;
}
