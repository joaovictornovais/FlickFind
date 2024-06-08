package br.com.flickfind.auth.repositories;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.dtos.RegisterRequestDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should find a user by email successfully")
    void findUserByEmailCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = createUser(register);

        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getEmail()).isEqualTo(register.email());

    }

    private User createUser(RegisterRequestDTO data) {
        User user = new User(data);
        this.entityManager.persist(user);
        return user;
    }



}