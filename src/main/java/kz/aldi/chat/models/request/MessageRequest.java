package kz.aldi.chat.models.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Long fromUserId;
    private Long toUserId;
    private Long toChatId;
    private String text;

}
