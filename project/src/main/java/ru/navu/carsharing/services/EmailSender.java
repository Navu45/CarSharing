package ru.navu.carsharing.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;


    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String subject, String message) throws MessagingException {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
            messageHelper.setTo(emailTo);
            messageHelper.setFrom(username);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
            mailSender.send(mail);
        }
        catch (MailException ignore) {}
    }
}
