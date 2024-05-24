package br.com.flickfind.auth.producers;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.dtos.EmailDTO;
import br.com.flickfind.auth.dtos.ProfileDTO;
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
    private String emailRoutingKey;

    @Value(value = "${broker.queue.profile.name}")
    private String profileRoutingKey;

    public void sendConfirmEmail(User user) {
        EmailDTO emailDTO = new EmailDTO(
                user.getId(),
                user.getEmail(),
                "Falta pouco para completar o seu cadastro! Confirme a sua conta",
                TemplateEmail.verificateAccount(user)
        );

        rabbitTemplate.convertAndSend("", emailRoutingKey, emailDTO);
    }

    public void createProfile(User user) {
        ProfileDTO profileDTO =
                new ProfileDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        rabbitTemplate.convertAndSend("", profileRoutingKey, profileDTO);
    }

}
