package com.example.emailnotification.service;

import com.example.emailnotification.model.EmailMessage;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MicrosoftGraphService implements EmailService {
    private final GraphServiceClient graphClient;
    private boolean isWatching = false;
    private String lastCheckedMessageId;

    @Override
    public List<EmailMessage> getNewEmails() {
        List<EmailMessage> newEmails = new ArrayList<>();
        try {
            graphClient.me()
                    .messages()
                    .buildRequest()
                    .filter("isRead eq false")
                    .get()
                    .getCurrentPage()
                    .forEach(message -> {
                        if (message.id.equals(lastCheckedMessageId)) {
                            return;
                        }
                        EmailMessage email = convertToEmailMessage(message);
                        newEmails.add(email);
                    });

            if (!newEmails.isEmpty()) {
                lastCheckedMessageId = newEmails.get(0).getId();
            }
        } catch (Exception e) {
            log.error("Error fetching Microsoft Graph messages", e);
        }
        return newEmails;
    }

    private EmailMessage convertToEmailMessage(Message message) {
        return EmailMessage.builder()
                .id(message.id)
                .subject(message.subject)
                .from(message.from.emailAddress.address)
                .body(message.body.content)
                .receivedTime(LocalDateTime.ofInstant(
                        message.receivedDateTime.toInstant(),
                        ZoneId.systemDefault()))
                .provider(EmailMessage.EmailProvider.MICROSOFT)
                .build();
    }

    @Override
    public void startEmailWatch() {
        isWatching = true;
    }

    @Override
    public void stopEmailWatch() {
        isWatching = false;
    }

    @Scheduled(fixedDelay = 60000) // Check every minute
    public void checkNewEmails() {
        if (isWatching) {
            List<EmailMessage> newEmails = getNewEmails();
            // Implement notification logic here
        }
    }
}
