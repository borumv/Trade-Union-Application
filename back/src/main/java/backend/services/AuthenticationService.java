package backend.services;

import backend.exceptions.ErrorNewPasswordException;
import backend.exceptions.UserNameNotFoundException;
import backend.persist.entity.User;
import backend.persist.repositories.PermoRepo;
import backend.persist.repositories.UserRepo;
import backend.requests.AuthenticationRequest;
import backend.requests.ChangePasswordRequest;
import backend.requests.RegisterRequest;
import backend.security.AuthenticationException;
import backend.security.JwtTokenProvider;
import backend.security.Role;
import backend.security.Status;
import backend.validator.ValidationProblem;
import backend.validator.error.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PermoRepo permoRepo;

    private final AuthenticationManager manager;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(AuthenticationManager manager, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {

        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Map<Object, Object> authenticate(AuthenticationRequest request) {

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getEmail());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            log.info("UserId: {}. Authenticate", request.getEmail());
            return response;
        } catch (org.springframework.security.core.AuthenticationException e) {
            List<ValidationError> errors = new ArrayList<>();
            ValidationProblem problem = new ValidationProblem();
            log.error("Authentication error {}", e.getMessage());
            errors.add(new ValidationError("password is not valid", "password", "validation problem"));
            log.error("Authentication error with email {} ", request.getEmail());
            problem.setErrors(errors);
            throw new AuthenticationException("Authentication error", HttpStatus.FORBIDDEN, errors);
        }
    }

    public Map<Object, Object> register(RegisterRequest request) {

        User registredUser = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
        save(registredUser);
        String token = jwtTokenProvider.createToken(registredUser.getEmail());
        Map<Object, Object> response = new HashMap<>();
        response.put("email", registredUser.getEmail());
        response.put("token", token);
        return response;
    }

    public User findByEmail(String email) {

        return userRepo.findByEmail(email).orElseThrow(() -> new UserNameNotFoundException("not found with email this - " + email));
    }

    public void save(User user) {

        userRepo.save(user);
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {

        User user = userRepo.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new UserNameNotFoundException(changePasswordRequest.getEmail()));
        if (changePasswordRequest.getActualPassword().equals(user.getPassword())) {
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepo.save(user);
        } else {
            throw new ErrorNewPasswordException("Password not concur");
        }
    }

}
