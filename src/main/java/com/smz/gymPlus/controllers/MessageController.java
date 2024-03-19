package com.smz.gymPlus.controllers;

import com.smz.gymPlus.dtos.MessageRequest;
import com.smz.gymPlus.models.Message;
import com.smz.gymPlus.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    @MessageMapping("/chat")
    public void sendMessage(
            @Payload Message message
    ) {
        Message savedMessage = messageService.saveMessage(message);
        messagingTemplate.convertAndSendToUser(
                savedMessage.getRecipient().getUsername(),
                "/queue/messages",
                MessageRequest.builder()
                        .messageContent(savedMessage.getContent())
                        .recipientUsername(savedMessage.getRecipient().getUsername())
                        .senderUsername(savedMessage.getSender().getUsername())
                        .build()
        );


    }

    @GetMapping("/messages/{senderUsername}/{recipientUsername}")
    public ResponseEntity<List<Message>> findChatMessages(
            @PathVariable("senderUsername") String senderUsername,
            @PathVariable("recipientUsername") String recipientUsername
    ) {
        return ResponseEntity.ok(messageService.findChatMessages(senderUsername, recipientUsername));
    }

}
