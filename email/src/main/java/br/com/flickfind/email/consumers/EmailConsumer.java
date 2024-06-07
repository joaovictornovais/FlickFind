package br.com.flickfind.email.consumers;

import br.com.flickfind.email.domain.email.Email;
import br.com.flickfind.email.dtos.EmailDTO;
import br.com.flickfind.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void sendEmail(@Payload EmailDTO emailDTO) {
        emailService.sendEmail(new Email(emailDTO));
    }

}
