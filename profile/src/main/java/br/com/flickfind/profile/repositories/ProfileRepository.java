package br.com.flickfind.profile.repositories;

import br.com.flickfind.profile.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
