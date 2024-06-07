package br.com.flickfind.profile.services;

import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.dtos.ProfileAdditionalInfoDTO;
import br.com.flickfind.profile.dtos.ProfileDTO;
import br.com.flickfind.profile.dtos.TokenResponseDTO;
import br.com.flickfind.profile.exceptions.EntityNotFoundException;
import br.com.flickfind.profile.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final FilterService filterService;

    public ProfileService(ProfileRepository profileRepository, FilterService filterService) {
        this.profileRepository = profileRepository;
        this.filterService = filterService;
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile createProfile(ProfileDTO profileDTO) {
        return profileRepository.save(new Profile(profileDTO));
    }

    public Profile editProfile(TokenResponseDTO tokenResponseDTO, ProfileAdditionalInfoDTO newProfile) {
        Profile profile = findByEmail(tokenResponseDTO.email());

        profile.setAvatar(newProfile.avatar());
        profile.setBirthDate(newProfile.birthDate());

        return profileRepository.save(profile);
    }

    public Profile findByEmail(String email) {
        return profileRepository.findByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("Profile not found"));
    }

}
