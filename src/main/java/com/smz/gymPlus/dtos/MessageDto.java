package com.smz.gymPlus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private String senderUsername;
    private String recipientUsername;
    private String messageContent;
    private LocalDateTime timestamp;

}
