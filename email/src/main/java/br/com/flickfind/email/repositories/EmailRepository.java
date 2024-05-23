package br.com.flickfind.email.repositories;

import br.com.flickfind.email.domain.email.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {
}
