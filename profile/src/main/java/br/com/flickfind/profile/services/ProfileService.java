package br.com.flickfind.profile.services;

import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.dtos.ProfileDTO;
import br.com.flickfind.profile.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(ProfileDTO profileDTO) {
        return profileRepository.save(new Profile(profileDTO));
    }

}
