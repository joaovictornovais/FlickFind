package br.com.flickfind.profile.consumer;

import br.com.flickfind.profile.dtos.ProfileDTO;
import br.com.flickfind.profile.services.ProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProfileConsumer {

    private final ProfileService profileService;

    public ProfileConsumer(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RabbitListener(queues = "${broker.queue.profile.name}")
    public void listener(@Payload ProfileDTO profileDTO) {
        profileService.createProfile(profileDTO);
    }

}
