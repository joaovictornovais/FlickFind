package br.com.flickfind.auth.dtos;

public record RegisterRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
