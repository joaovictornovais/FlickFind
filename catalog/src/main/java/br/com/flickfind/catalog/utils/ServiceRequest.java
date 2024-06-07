package br.com.flickfind.catalog.utils;

import br.com.flickfind.catalog.dtos.FilterDTO;
import br.com.flickfind.catalog.dtos.TokenResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceRequest {

    @Value("${api.security.token.validation.url}")
    private String tokenValidationUrl;

    @Value("${api.profiles.url}")
    private String profileUrl;

    public TokenResponseDTO returnTokenEmail(String token) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TokenResponseDTO> response =
                restTemplate.postForEntity(tokenValidationUrl, token, TokenResponseDTO.class);
        return response.getBody();
    }

    public FilterDTO getUserFilter(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", request.getHeader("Authorization"));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<FilterDTO> response = restTemplate.exchange(
                profileUrl, HttpMethod.GET, entity, FilterDTO.class
        );

        return response.getBody();
    }

}
