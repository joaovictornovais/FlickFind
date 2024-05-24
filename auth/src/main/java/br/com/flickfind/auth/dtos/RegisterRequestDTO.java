package br.com.flickfind.auth.dtos;

import java.time.LocalDate;

public record RegisterRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
