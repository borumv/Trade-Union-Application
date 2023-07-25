package backend.controllers;

import backend.exceptions.ErrorNewPasswordException;
import backend.persist.entity.Permission;
import backend.persist.entity.User;
import backend.persist.models.UserModel;
import backend.persist.models.UserWithAuthoritiesModel;
import backend.requests.ChangePasswordRequest;
import backend.security.Role;
import backend.services.AuthenticationService;
import backend.services.PermissionService;
import backend.validator.ValidationProblem;
import backend.validator.error.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UserController class handles requests related to users.
 * <p>
 * It provides endpoints for retrieving user information, changing passwords, and managing permissions.
 *
 * @author Boris Vlasevsky
 */
@RestController
@Validated
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin(origins = {"cors.allow"})
public class UserController {

    @Autowired
    AuthenticationService userService;
    @Autowired
    PermissionService permissionService;

    /**
     * Retrieves the user with the specified email.
     *
     * @param email the email of the user.
     * @return a User object representing the user.
     */
    @GetMapping("/findEmail")
    public User getByEmail(String email) {

        log.info("UserId: {}. Action: getByEmail", "UserController");
        return userService.findByEmail(email);
    }

    /**
     * Retrieves the authenticated user with authorities.
     *
     * @return a UserWithAuthoritiesModel object representing the authenticated user.
     */
    @GetMapping()
    @Transactional
    public UserWithAuthoritiesModel getUser() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        List<Permission> permissions = permissionService.getPermission(user.getRole());
        return UserWithAuthoritiesModel.toModel(user, permissions);
    }

    /**
     * Retrieves the authenticated user.
     *
     * @return a UserModel object representing the authenticated user.
     */
    public UserModel getActualUser() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        return UserModel.toModel(user);
    }

    /**
     * Retrieves the list of permissions for the specified role.
     *
     * @param role the role for which permissions are to be retrieved.
     * @return a list of Permission objects representing the permissions.
     */
    @GetMapping("/permissions")
    public List<Permission> getPermissionList(Role role) {

        return permissionService.getPermission(role);
    }

    /**
     * Changes the password for the user with the specified email.
     *
     * @param req the ChangePasswordRequest object containing the email and new password.
     * @return a ResponseEntity containing the result of the password change operation.
     */
    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(
            @RequestBody
            ChangePasswordRequest req) {

        try {
            Map<Object, Object> response = new HashMap<>();
            response.put("email", req.getEmail());
            userService.changePassword(req);
            return ResponseEntity.ok(req);
        } catch (ErrorNewPasswordException e) {
            List<ValidationError> errors = new ArrayList<>();
            log.error("Authentication error {}", e.getMessage());
            e.printStackTrace();
            errors.add(new ValidationError("incorrect active password", "activePassword", "password change problem"));
            ValidationProblem problem = new ValidationProblem();
            problem.setErrors(errors);
            log.error("Authentication error with email {} ", req.getEmail());
            return new ResponseEntity<>(problem, HttpStatus.FORBIDDEN);
        }
    }
}
