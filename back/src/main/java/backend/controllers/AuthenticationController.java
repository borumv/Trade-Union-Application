/**
 * The AuthenticationController class handles authentication and registration requests.
 * It is responsible for authenticating users, logging them out, and registering new users.
 * @author Boris Vlasevsky
 */

package backend.controllers;

import backend.requests.AuthenticationRequest;
import backend.requests.RegisterRequest;
import backend.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@Validated
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Constructs a new AuthenticationController with the specified AuthenticationService.
     *
     * @param authenticationService the AuthenticationService used for authentication and registration.
     */
    public AuthenticationController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param request the authentication request containing the user's credentials.
     * @return a ResponseEntity with the authenticated user information.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody
            AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * Logs out the currently authenticated user.
     *
     * @param request  the HttpServletRequest representing the current request.
     * @param response the HttpServletResponse representing the current response.
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    /**
     * Registers a new user with the provided registration details.
     *
     * @param request the registration request containing the user's details.
     * @return a ResponseEntity with the registered user information.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody
            RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));
    }
}