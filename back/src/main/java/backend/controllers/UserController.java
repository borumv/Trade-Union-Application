package backend.controllers;

import backend.exceptions.ErrorNewPasswordException;
import backend.persist.entity.Permission;
import backend.persist.entity.User;
import backend.persist.models.UserModel;
import backend.persist.models.UserWithAuthoritiesModel;
import backend.requests.ChangePasswordRequestr;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000"})
@Slf4j
public class UserController {

    @Autowired
    AuthenticationService userService;
    @Autowired
    PermissionService permissionService;

    @GetMapping("/findEmail")
    public User getByEmail(String email) {

        log.info("UserId: {}. Action: getByEmail", "UserController");
        return userService.findByEmail(email);
    }

    @GetMapping()
    public UserWithAuthoritiesModel getUser() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        List<Permission> permissions = permissionService.getPermission(user.getRole());
        return UserWithAuthoritiesModel.toModel(user, permissions);
    }

    public UserModel getActualUser() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        return UserModel.toModel(user);
    }

    @GetMapping("/permissions")
    public List<Permission> getPermissionList(Role role) {

        return permissionService.getPermission(role);
    }

    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(
            @RequestBody
            ChangePasswordRequestr req) {

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
