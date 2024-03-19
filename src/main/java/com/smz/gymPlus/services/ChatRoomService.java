package com.smz.gymPlus.services;

import com.smz.gymPlus.models.ChatRoom;
import com.smz.gymPlus.repositories.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomName(
            String senderUsername,
            String recipientUsername,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository.findBySenderUsernameAndRecipientUsername(senderUsername, recipientUsername)
                .map(ChatRoom::getChatName)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatName = createChatName(senderUsername, recipientUsername);
                        return Optional.of(chatName);
                    }
                    return Optional.empty();
                });
    }

    private String createChatName(String senderUsername, String recipientUsername) {

        var chatName = String.format("%s_%s", senderUsername, recipientUsername);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatName(chatName)
                .senderUsername(senderUsername)
                .recipientUsername(recipientUsername)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatName(chatName)
                .senderUsername(recipientUsername)
                .recipientUsername(senderUsername)
                .build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatName;

    }

}
