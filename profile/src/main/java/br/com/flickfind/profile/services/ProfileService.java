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

    private ProfileRepository profileRepository;
    private final TokenValidationService tokenValidationService;

    public ProfileService(ProfileRepository profileRepository, TokenValidationService tokenValidationService) {
        this.profileRepository = profileRepository;
        this.tokenValidationService = tokenValidationService;
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
