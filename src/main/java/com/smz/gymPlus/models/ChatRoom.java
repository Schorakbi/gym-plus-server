package com.smz.gymPlus.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue
    private String id;
    private String chatName;
    private String senderUsername;
    private String recipientUsername;

}
