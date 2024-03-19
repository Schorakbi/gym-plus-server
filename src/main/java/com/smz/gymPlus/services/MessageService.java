package com.smz.gymPlus.services;

import com.smz.gymPlus.models.Message;
import com.smz.gymPlus.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;


    public Message saveMessage(Message message) {
        var chatRoomName = chatRoomService.getChatRoomName(
                message.getSender().getUsername(),
                message.getRecipient().getUsername(),
                true
        ).orElseThrow();
        message.setChatRoomName(chatRoomName);
        return messageRepository.save(message);
    }

    public List<Message> findChatMessages(
            String senderUsername,
            String recipientUsername

    ) {
        var chatRoomName = chatRoomService.getChatRoomName(senderUsername,
                recipientUsername,
                false);
        return chatRoomName.map(messageRepository::findByChatRoomName).orElse(new ArrayList<>());
    }


}
