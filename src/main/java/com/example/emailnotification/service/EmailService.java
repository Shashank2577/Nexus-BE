package com.example.emailnotification.service;

import com.example.emailnotification.model.EmailMessage;
import java.util.List;

public interface EmailService {
    List<EmailMessage> getNewEmails();
    void startEmailWatch();
    void stopEmailWatch();
}
