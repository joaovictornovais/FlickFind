package br.com.flickfind.profile.consumer;

import br.com.flickfind.profile.domain.filter.Filter;
import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.dtos.ProfileDTO;
import br.com.flickfind.profile.services.FilterService;
import br.com.flickfind.profile.services.ProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProfileConsumer {

    private final ProfileService profileService;
    private final FilterService filterService;

    public ProfileConsumer(ProfileService profileService, FilterService filterService) {
        this.profileService = profileService;
        this.filterService = filterService;
    }

    @RabbitListener(queues = "${broker.queue.profile.name}")
    public void listener(@Payload ProfileDTO profileDTO) {
        Profile profile = profileService.createProfile(profileDTO);
        Filter filter = new Filter();
        filter.setProfile(profile);
        filter = filterService.save(filter);
        profile.setFilter(filter);
        profileService.save(profile);

    }

}
