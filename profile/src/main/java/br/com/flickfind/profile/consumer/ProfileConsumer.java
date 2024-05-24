package br.com.flickfind.profile.consumer;

import br.com.flickfind.profile.dtos.ProfileDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProfileConsumer {

    @RabbitListener(queues = "${broker.queue.profile.name}")
    public void listener(@Payload ProfileDTO userDTO) {
        System.out.println(userDTO.id());
    }

}
