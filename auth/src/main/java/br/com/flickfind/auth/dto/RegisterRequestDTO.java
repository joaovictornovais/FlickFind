package br.com.flickfind.auth.dto;

import java.time.LocalDate;

public record RegisterRequestDTO(
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        String password
) {
}
