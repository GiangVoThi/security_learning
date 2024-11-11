package com.example.study_security.service;

import com.example.study_security.data.dto.request.MailBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendSimpleMessage(MailBody mailbody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailbody.to());
        message.setFrom("baothiien2004@gmail.com");
        message.setSubject(mailbody.subject());
        message.setText(mailbody.text());

        javaMailSender.send(message);

    }

}
