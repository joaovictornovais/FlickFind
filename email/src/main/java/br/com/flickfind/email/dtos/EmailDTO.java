package br.com.flickfind.email.dtos;

public record EmailDTO(
        String userId,
        String emailTo,
        String subject,
        String text
) {
}
