package backend.controllers;

import backend.exceptions.ErrorNewPasswordException;
import backend.persist.entity.Permission;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.User;
import backend.persist.models.UserModel;
import backend.persist.models.UserWithAuthoritiesModel;
import backend.requests.ChangePasswordRequestr;
import backend.security.Role;
import backend.services.PermissionService;
import backend.services.UserService;
import backend.validator.ValidationProblem;
import backend.validator.error.ValidationError;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionSevice;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/findEmail")
    public User getByEmail(String email) {
        logger.info("UserId: {}. Action: getByEmail", "UserController");
        return userService.findByEmail(email);
    }

    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping()
    public UserWithAuthoritiesModel getUser() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        List<Permission> permissions = permissionSevice.getPermission(user.getRole());
        return UserWithAuthoritiesModel.toModel(user, permissions);
    }

    public UserModel getActualUser(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(a.getName());
        return UserModel.toModel(user);
    }

    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/permissions")
    public List<Permission> getPermisionList(Role role){
        return permissionSevice.getPermission(role);
    }


    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestr req) {
       try {
           Map<Object, Object> response = new HashMap<>();
           response.put("email",req.getEmail());
           userService.changePassword(req);
           return ResponseEntity.ok(req);
       }catch (ErrorNewPasswordException e){
           List<ValidationError> errors = new ArrayList<>();
           logger.error("Authentication error {}", e.getMessage());
           e.printStackTrace();
           errors.add(new ValidationError("incorrect active password", "activePassword", "password change problem"));
           ValidationProblem problem = new ValidationProblem();
           problem.setErrors(errors);
           logger.error("Authentication error with email {} ", req.getEmail());
           return new ResponseEntity<>(problem, HttpStatus.FORBIDDEN);
       }
    }

}
