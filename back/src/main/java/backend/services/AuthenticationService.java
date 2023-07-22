package backend.services;

import backend.exceptions.ErrorNewPasswordException;
import backend.exceptions.UserNameNotFoundException;
import backend.persist.entity.RefreshToken;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for authentication-related operations
 *
 * @author Boris Vlasevsky
 */

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

    private final RefreshTokenService refreshTokenService;



    /**
     * Constructs an AuthenticationService with the specified dependencies.
     *
     * @param manager             the AuthenticationManager
     * @param passwordEncoder     the PasswordEncoder
     * @param jwtTokenProvider    the JwtTokenProvider
     * @param refreshTokenService the RefreshToken
     */
    public AuthenticationService(AuthenticationManager manager, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {

        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }



    /**
     * Authenticates a user based on the provided AuthenticationRequest.
     *
     * @param request the AuthenticationRequest object
     * @return a map containing the email and token of the authenticated user
     * @throws AuthenticationException if authentication fails
     */
    @Transactional
    public Map<Object, Object> authenticate(AuthenticationRequest request) throws AuthenticationException {

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getEmail());
            var refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            response.put("refresh_token", refreshToken.getToken());
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

    /**
     * Registers a user based on the provided RegisterRequest.
     *
     * @param request the RegisterRequest object
     * @return a map containing the email and token of the registered user
     */
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

    /**
     * Retrieves a user by the specified email.
     *
     * @param email the email of the user
     * @return the retrieved User object
     * @throws UserNameNotFoundException if no user is found with the specified email
     */
    public User findByEmail(String email) throws UserNameNotFoundException {

        return userRepo.findByEmail(email).orElseThrow(() -> new UserNameNotFoundException("not found with email this - " + email));
    }

    /**
     * Saves a user.
     *
     * @param user the User object to be saved
     */
    public void save(User user) {

        userRepo.save(user);
    }

    /**
     * Changes the password of a user based on the provided ChangePasswordRequest.
     *
     * @param changePasswordRequest the ChangePasswordRequest object
     * @throws UserNameNotFoundException if no user is found with the specified email
     * @throws ErrorNewPasswordException if the actual password does not match the user's current password
     */
    public void changePassword(ChangePasswordRequest changePasswordRequest) throws UserNameNotFoundException, ErrorNewPasswordException {

        User user = userRepo.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new UserNameNotFoundException(changePasswordRequest.getEmail()));
        if (changePasswordRequest.getActualPassword().equals(user.getPassword())) {
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepo.save(user);
        } else {
            throw new ErrorNewPasswordException("Password not concur");
        }
    }

    public String generateTokenByUserName(String name){
        return jwtTokenProvider.createToken(name);
    }
}