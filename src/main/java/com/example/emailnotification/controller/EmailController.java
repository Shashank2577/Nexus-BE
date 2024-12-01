package com.example.emailnotification.controller;

import com.example.emailnotification.model.EmailMessage;
import com.example.emailnotification.service.GmailService;
import com.example.emailnotification.service.MicrosoftGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final GmailService gmailService;
    private final MicrosoftGraphService microsoftGraphService;

    @GetMapping("/new")
    public ResponseEntity<List<EmailMessage>> getNewEmails() {
        List<EmailMessage> allNewEmails = new ArrayList<>();
        allNewEmails.addAll(gmailService.getNewEmails());
        allNewEmails.addAll(microsoftGraphService.getNewEmails());
        return ResponseEntity.ok(allNewEmails);
    }

    @GetMapping("/watch/start")
    public ResponseEntity<Void> startWatching() {
        gmailService.startEmailWatch();
        microsoftGraphService.startEmailWatch();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/watch/stop")
    public ResponseEntity<Void> stopWatching() {
        gmailService.stopEmailWatch();
        microsoftGraphService.stopEmailWatch();
        return ResponseEntity.ok().build();
    }
}
