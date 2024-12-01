package com.example.emailnotification.service;

import com.example.emailnotification.model.EmailMessage;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GmailService implements EmailService {
    private final Gmail gmail;
    private boolean isWatching = false;
    private String lastCheckedMessageId;

    @Override
    public List<EmailMessage> getNewEmails() {
        List<EmailMessage> newEmails = new ArrayList<>();
        try {
            ListMessagesResponse response = gmail.users().messages()
                    .list("me")
                    .setQ("is:unread")
                    .execute();

            if (response.getMessages() != null) {
                for (Message message : response.getMessages()) {
                    if (message.getId().equals(lastCheckedMessageId)) {
                        break;
                    }

                    Message fullMessage = gmail.users().messages()
                            .get("me", message.getId())
                            .setFormat("full")
                            .execute();

                    EmailMessage email = convertToEmailMessage(fullMessage);
                    newEmails.add(email);
                }

                if (!response.getMessages().isEmpty()) {
                    lastCheckedMessageId = response.getMessages().get(0).getId();
                }
            }
        } catch (IOException e) {
            log.error("Error fetching Gmail messages", e);
        }
        return newEmails;
    }

    private EmailMessage convertToEmailMessage(Message message) {
        final String[] subject = {""};
        final String[] from = {""};
        
        message.getPayload().getHeaders().forEach(header -> {
            if (header.getName().equals("Subject")) {
                subject[0] = header.getValue();
            } else if (header.getName().equals("From")) {
                from[0] = header.getValue();
            }
        });

        String body = "";
        if (message.getPayload().getBody() != null && message.getPayload().getBody().decodeData() != null) {
            body = new String(message.getPayload().getBody().decodeData());
        }

        return EmailMessage.builder()
                .id(message.getId())
                .subject(subject[0])
                .from(from[0])
                .body(body)
                .receivedTime(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(message.getInternalDate()),
                        ZoneId.systemDefault()))
                .provider(EmailMessage.EmailProvider.GMAIL)
                .build();
    }

    @Override
    public void startEmailWatch() {
        isWatching = true;
        log.info("Started watching Gmail for new emails");
    }

    @Override
    public void stopEmailWatch() {
        isWatching = false;
        log.info("Stopped watching Gmail for new emails");
    }

    @Scheduled(fixedDelay = 60000) // Check every minute
    public void checkNewEmails() {
        if (isWatching) {
            try {
                List<EmailMessage> newEmails = getNewEmails();
                if (!newEmails.isEmpty()) {
                    log.info("Found {} new emails", newEmails.size());
                    // TODO: Implement your notification logic here
                    // For now, just log the new emails
                    newEmails.forEach(email -> 
                        log.info("New email: From={}, Subject={}", email.getFrom(), email.getSubject())
                    );
                }
            } catch (Exception e) {
                log.error("Error checking new emails", e);
            }
        }
    }
}
