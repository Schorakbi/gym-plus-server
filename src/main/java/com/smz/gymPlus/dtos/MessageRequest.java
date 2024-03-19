package com.smz.gymPlus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {
        private String senderUsername;
        private String recipientUsername;
        private String messageContent;
}
