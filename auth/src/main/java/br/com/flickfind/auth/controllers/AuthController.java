package br.com.flickfind.auth.controllers;

import br.com.flickfind.auth.dtos.LoginRequestDTO;
import br.com.flickfind.auth.dtos.RegisterRequestDTO;
import br.com.flickfind.auth.dtos.ResponseDTO;
import br.com.flickfind.auth.dtos.VerificateAccountDTO;
import br.com.flickfind.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/activate/{id}", params = "key")
    public ResponseEntity<Void> activate(@PathVariable String id, @RequestParam String key) {
        userService.activateUser(new VerificateAccountDTO(id, key));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
