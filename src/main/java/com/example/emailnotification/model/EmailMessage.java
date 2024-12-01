package com.example.emailnotification.model;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String id;
    private String from;
    private String subject;
    private String body;
    private LocalDateTime receivedTime;
    private EmailProvider provider;
    
    public enum EmailProvider {
        GMAIL,
        MICROSOFT
    }
}
