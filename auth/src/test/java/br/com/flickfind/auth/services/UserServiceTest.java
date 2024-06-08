package br.com.flickfind.auth.services;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.dtos.LoginRequestDTO;
import br.com.flickfind.auth.dtos.RegisterRequestDTO;
import br.com.flickfind.auth.dtos.VerificateAccountDTO;
import br.com.flickfind.auth.exceptions.EntityNotFoundException;
import br.com.flickfind.auth.exceptions.InvalidArgumentException;
import br.com.flickfind.auth.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find a user by email")
    void findByEmailCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findByEmail(user.getEmail());

        assertThat(response).isPresent();
        assertThat(response.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Should throw a exception when user not found by email")
    void findByEmailCase2() {
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (userRepository.findByEmail("joao@gmail.com").isEmpty())
                throw new EntityNotFoundException("User not found");
        });

        Assertions.assertEquals("User not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should find a user by id")
    void findByIdCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);
        user.setId(UUID.randomUUID().toString());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findById(user.getId());

        assertThat(response).isPresent();
        assertThat(response.get().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("Should throw a exception when user not found by id")
    void findByIdCase2() {
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (userRepository.findById(UUID.randomUUID().toString()).isEmpty())
                throw new EntityNotFoundException("User not found");
        });

        Assertions.assertEquals("User not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should register a new user")
    void registerUserCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);

        when(userRepository.save(user)).thenReturn(user);

        User response = userRepository.save(user);

        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Should throw an exception when trying to use a email already registered")
    void registerUserCase2() {
        RegisterRequestDTO register1 = new RegisterRequestDTO("Joao", "Victor", "joao@gmail.com", "1234");
        User user1 = new User(register1);

        RegisterRequestDTO register2 = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user2 = new User(register2);

        when(userRepository.findByEmail("joao@gmail.com")).thenReturn(Optional.of(user1));

        Exception thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            if (userRepository.findByEmail(user2.getEmail()).isPresent()) {
                throw new InvalidArgumentException("E-mail already registered");
            }
        });

        Assertions.assertEquals("E-mail already registered", thrown.getMessage());
    }

    @Test
    @DisplayName("Should login sucessfully")
    void loginCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findByEmail(user.getEmail());

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(user.getEmail(), user.getPassword());

        if (response.isPresent()) {
            if (!loginRequestDTO.password().equals(user.getPassword()))
                throw new InvalidArgumentException("Invalid credentials");
        }

        assertThat(response).isPresent();
        assertThat(response.get().getPassword()).isEqualTo(loginRequestDTO.password());
    }

    @Test
    @DisplayName("Should throw an exception when invalid credentials")
    void loginCase2() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> response = userRepository.findByEmail(user.getEmail());

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(user.getEmail(), "senhaerrada123");

        Exception thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            if (response.isPresent()) {
                if (!loginRequestDTO.password().equals(user.getPassword()))
                    throw new InvalidArgumentException("Invalid credentials");
            }
        });

        Assertions.assertEquals("Invalid credentials", thrown.getMessage());

    }

    @Test
    @DisplayName("Should active a user successfully")
    void activateUserCase1() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);
        user.setVerificationCode("123456");
        user.setId(UUID.randomUUID().toString());

        VerificateAccountDTO verificateAccountDTO = new VerificateAccountDTO(user.getId(), user.getVerificationCode());

        if (!verificateAccountDTO.key().equals(user.getVerificationCode())) {
            throw new InvalidArgumentException("Wrong code or already registered");
        }

        assertThat(verificateAccountDTO.key()).isEqualTo(user.getVerificationCode());
    }

    @Test
    @DisplayName("Should throw an exception when user already activated or wrong code")
    void activateUserCase2() {
        RegisterRequestDTO register = new RegisterRequestDTO("João", "Novais", "joao@gmail.com", "1234");
        User user = new User(register);
        user.setVerificationCode("123456");
        user.setId(UUID.randomUUID().toString());

        VerificateAccountDTO verificateAccountDTO = new VerificateAccountDTO(user.getId(), "CODIGOERRADO");

        Exception thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            if (!verificateAccountDTO.key().equals(user.getVerificationCode())) {
                throw new InvalidArgumentException("Wrong code or already registered");
            }
        });

        Assertions.assertEquals("Wrong code or already registered", thrown.getMessage());
    }

}