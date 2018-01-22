package com.anteastra.devopsbuddy.backend.service;

import com.anteastra.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);
    public void sendGenericEmailMessage(SimpleMailMessage message);
}
