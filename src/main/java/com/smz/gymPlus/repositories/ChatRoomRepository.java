package com.smz.gymPlus.repositories;

import com.smz.gymPlus.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {


    Optional<ChatRoom> findBySenderUsernameAndRecipientUsername(String senderUsername, String recipientUsername);
}
