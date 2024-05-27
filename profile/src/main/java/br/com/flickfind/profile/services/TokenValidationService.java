package br.com.flickfind.profile.services;

import br.com.flickfind.profile.dtos.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {

    @Value("${api.security.token.validation.url}")
    private String tokenValidationUrl;

    public TokenResponseDTO returnTokenEmail(String token) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TokenResponseDTO> response =
                restTemplate.postForEntity(tokenValidationUrl, token, TokenResponseDTO.class);
        return response.getBody();
    }

}
