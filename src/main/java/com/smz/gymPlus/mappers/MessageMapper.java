package com.smz.gymPlus.mappers;

import com.smz.gymPlus.dtos.MessageDto;
import com.smz.gymPlus.models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {

    public MessageDto fromMessageToMessageResponse(Message message) {
        return new MessageDto(message.getSender().getUsername(), message.getRecipient().getUsername(), message.getContent(), message.getTimestamp());
    }

}
