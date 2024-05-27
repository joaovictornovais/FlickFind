package br.com.flickfind.profile.repositories;

import br.com.flickfind.profile.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    Optional<Profile> findByEmail(String email);

}
