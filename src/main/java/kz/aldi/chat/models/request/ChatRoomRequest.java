package kz.aldi.chat.models.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRoomRequest {
    private String name;
    private List<Long> userIdList = new ArrayList<>();
}
