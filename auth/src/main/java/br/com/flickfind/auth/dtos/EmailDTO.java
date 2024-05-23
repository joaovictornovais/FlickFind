package br.com.flickfind.auth.dtos;

public record EmailDTO(
        String userId,
        String emailTo,
        String subject,
        String text
) {
}
