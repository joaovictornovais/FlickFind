package br.com.flickfind.auth.services;

import br.com.flickfind.auth.domain.user.User;
import br.com.flickfind.auth.domain.user.UserRole;
import br.com.flickfind.auth.dtos.LoginRequestDTO;
import br.com.flickfind.auth.dtos.RegisterRequestDTO;
import br.com.flickfind.auth.dtos.ResponseDTO;
import br.com.flickfind.auth.dtos.VerificateAccountDTO;
import br.com.flickfind.auth.exceptions.EntityNotFoundException;
import br.com.flickfind.auth.exceptions.InvalidArgumentException;
import br.com.flickfind.auth.infra.security.TokenService;
import br.com.flickfind.auth.producers.UserProducer;
import br.com.flickfind.auth.repositories.UserRepository;
import br.com.flickfind.auth.utils.GenerateVerificationCode;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       TokenService tokenService, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userProducer = userProducer;
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) throw new EntityNotFoundException("User not found");
        return user.get();
    }

    @Transactional
    public ResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        try {
            findByEmail(registerRequestDTO.email());
        } catch (EntityNotFoundException e) {
            User user = new User(registerRequestDTO);
            user.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
            user.setVerificationCode(GenerateVerificationCode.generateVerificationCode());
            user.setRole(UserRole.USER);
            user = userRepository.save(user);

            userProducer.sendConfirmEmail(user);
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

    public void activeUser(VerificateAccountDTO verificateAccountDTO) {
        User user = findByEmail(verificateAccountDTO.email());
        if (user.getVerificationCode().equals(verificateAccountDTO.code()) && !user.isEnabled())
            user.setEnabled(true);
        else throw new InvalidArgumentException("Wrong code or user is already activated");
    }

    private String getUserFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

}
