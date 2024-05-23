package br.com.flickfind.auth.controllers;

import br.com.flickfind.auth.dto.LoginRequestDTO;
import br.com.flickfind.auth.dto.RegisterRequestDTO;
import br.com.flickfind.auth.dto.ResponseDTO;
import br.com.flickfind.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerRequestDTO));
    }

}
