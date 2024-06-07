package br.com.flickfind.auth.controllers;

import br.com.flickfind.auth.dtos.TokenResponseDTO;
import br.com.flickfind.auth.infra.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponseDTO> validateToken(@RequestBody String token) {
        String validateToken = tokenService.validateToken(token);
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(validateToken);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDTO);
    }

}
