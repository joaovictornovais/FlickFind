package br.com.flickfind.profile.controllers;

import br.com.flickfind.profile.configs.Patcher;
import br.com.flickfind.profile.domain.filter.Filter;
import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.dtos.ProfileAdditionalInfoDTO;
import br.com.flickfind.profile.dtos.TokenResponseDTO;
import br.com.flickfind.profile.exceptions.UnauthorizedException;
import br.com.flickfind.profile.services.FilterService;
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
    private final FilterService filterService;
    private final TokenValidationService tokenValidationService;

    private final Patcher patcher;

    public ProfileController(TokenValidationService tokenValidationService,
                             ProfileService profileService,
                             Patcher patcher,
                             FilterService filterService) {
        this.tokenValidationService = tokenValidationService;
        this.profileService = profileService;
        this.patcher = patcher;
        this.filterService = filterService;
    }

    @PutMapping
    public ResponseEntity<Profile> editProfileAdditionalInfo(@RequestBody ProfileAdditionalInfoDTO profileAdditionalInfoDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        TokenResponseDTO response = tokenValidationService.returnTokenEmail(token);
        return ResponseEntity.status(HttpStatus.OK).body(profileService.editProfile(response, profileAdditionalInfoDTO));
    }

    @PatchMapping
    public ResponseEntity<Filter> patchFilter(@RequestBody Filter incompleteFilter, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        TokenResponseDTO response = tokenValidationService.returnTokenEmail(token);
        Filter existingFilter = profileService.findByEmail(response.email()).getFilter();
        try {
            patcher.internPatcher(existingFilter, incompleteFilter);
            filterService.save(existingFilter);
        } catch (IllegalAccessException e) {
            throw new UnauthorizedException("You need to login");
        }
        return ResponseEntity.status(HttpStatus.OK).body(existingFilter);
    }

}
