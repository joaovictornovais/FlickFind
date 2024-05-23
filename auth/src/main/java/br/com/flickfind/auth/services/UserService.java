package br.com.flickfind.auth.services;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.dto.LoginRequestDTO;
import br.com.flickfind.auth.dto.RegisterRequestDTO;
import br.com.flickfind.auth.dto.ResponseDTO;
import br.com.flickfind.auth.exceptions.EntityNotFoundException;
import br.com.flickfind.auth.exceptions.InvalidArgumentException;
import br.com.flickfind.auth.infra.security.TokenService;
import br.com.flickfind.auth.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new EntityNotFoundException("User not found");
        return user.get();
    }

    public ResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        try {
            findByEmail(registerRequestDTO.email());
        } catch (EntityNotFoundException e) {
            User user = new User(registerRequestDTO);
            user.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
            user = userRepository.save(user);

            String token = tokenService.generateToken(user);
            return new ResponseDTO(getUserFullName(user), token);
        }
        throw new InvalidArgumentException("Email already registered");
    }

    public ResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = findByEmail(loginRequestDTO.email());
        if (passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new ResponseDTO(getUserFullName(user), token);
        }
        throw new InvalidArgumentException("Invalid credentials");
    }

    private String getUserFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

}
