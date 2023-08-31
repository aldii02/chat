package kz.aldi.chat.services;

import kz.aldi.chat.models.dto.MessageDto;
import kz.aldi.chat.models.entity.Message;
import kz.aldi.chat.models.request.MessageRequest;

import java.util.List;

public interface MessageService {
    List<Message> sendMessageToUser(MessageRequest message);
    List<Message> sendMessageToGroup(MessageRequest message);
    Message create(MessageDto messageDto);

}
