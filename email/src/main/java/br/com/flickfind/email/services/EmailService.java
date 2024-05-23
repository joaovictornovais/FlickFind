package br.com.flickfind.email.services;

import br.com.flickfind.email.domain.email.Email;
import br.com.flickfind.email.dtos.EmailDTO;
import br.com.flickfind.email.dtos.EmailStatus;
import br.com.flickfind.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public Email sendEmail(Email email) {
        try {
            email.setSendEmailDate(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);

            email.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e) {
            email.setEmailStatus(EmailStatus.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }

}