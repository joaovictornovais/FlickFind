package br.com.flickfind.auth.producers;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.dtos.EmailDTO;
import br.com.flickfind.auth.utils.TemplateEmail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void sendConfirmEmail(User user) {
        EmailDTO emailDTO = new EmailDTO(
                user.getId(),
                user.getEmail(),
                "Falta pouco para completar o seu cadastro! Confirme a sua conta",
                TemplateEmail.verificateAccount(user)
        );

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }

}
