package com.smz.gymPlus.repositories;

import com.smz.gymPlus.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{


    List<Message> findByChatRoomName(String s);
}
