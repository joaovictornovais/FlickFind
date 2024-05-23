package br.com.flickfind.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email should not be blank")
        @Email(message = "Email should be well formatted. Example: your_email@example.com")
        String email,
        @NotBlank(message = "Password should not be blank")
        String password) {
}
