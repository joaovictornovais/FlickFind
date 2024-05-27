package br.com.flickfind.profile.controllers;

import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.dtos.ProfileAdditionalInfoDTO;
import br.com.flickfind.profile.dtos.TokenResponseDTO;
import br.com.flickfind.profile.services.ProfileService;
import br.com.flickfind.profile.services.TokenValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final TokenValidationService tokenValidationService;

    public ProfileController(TokenValidationService tokenValidationService, ProfileService profileService) {
        this.tokenValidationService = tokenValidationService;
        this.profileService = profileService;
    }

    @PutMapping
    public ResponseEntity<Profile> editProfileAdditionalInfo(@RequestBody ProfileAdditionalInfoDTO profileAdditionalInfoDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        TokenResponseDTO response = tokenValidationService.returnTokenEmail(token);
        return ResponseEntity.status(HttpStatus.OK).body(profileService.editProfile(response, profileAdditionalInfoDTO));
    }

}
