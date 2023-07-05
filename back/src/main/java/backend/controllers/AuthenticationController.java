package backend.controllers;

import backend.exceptions.UserNameNotFoundException;
import backend.persist.entity.User;
import backend.persist.repositories.UserRepo;
import backend.requests.AuthenticationRequest;
import backend.security.JwtTokenProvider;
import backend.services.UserService;
import backend.validator.ValidationProblem;
import backend.validator.error.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@Validated
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthenticationController {

    private final AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    private  UserController userController;
    private JwtTokenProvider jwtTokenProvider;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationManager manager, UserRepo userRepo, JwtTokenProvider jwtTokenProvider) {
        this.manager = manager;

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        try{
            User user = userController.getByEmail(request.getEmail());
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email",request.getEmail());
            response.put("token", token);
            logger.info("UserId: {}. Authenticate",   request.getEmail());
            return ResponseEntity.ok(response);
        }
        catch (UserNameNotFoundException e){
            List<ValidationError> errors = new ArrayList<>();
            errors.add(new ValidationError("Incorrect email", "email", "validation problem"));
            ValidationProblem problem = new ValidationProblem();
            problem.setErrors(errors);
            logger.error("Username not found -  {}", request.getEmail());
            return new ResponseEntity<>(problem, HttpStatus.FORBIDDEN);
        }
        catch (AuthenticationException e){
             List<ValidationError> errors = new ArrayList<>();
            logger.error("Authentication error {}", e.getMessage());
             e.printStackTrace();
             errors.add(new ValidationError("password is not valid", "password", "validation problem"));
             ValidationProblem problem = new ValidationProblem();
             problem.setErrors(errors);
            logger.error("Authentication error with email {} ", request.getEmail());
            return new ResponseEntity<>(problem, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}