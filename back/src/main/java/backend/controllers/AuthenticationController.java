package backend.controllers;

import backend.persist.repositories.UserRepo;
import backend.requests.AuthenticationRequest;
import backend.requests.RegisterRequest;
import backend.security.JwtTokenProvider;
import backend.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@Validated
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final AuthenticationService authenticationService;

    @Autowired
    private UserController userController;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager manager,
                                    UserRepo userRepo,
                                    AuthenticationService authenticationService, JwtTokenProvider jwtTokenProvider,
                                    PasswordEncoder passwordEncoder) {

        this.manager = manager;
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody
            AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody
            RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));
    }
}