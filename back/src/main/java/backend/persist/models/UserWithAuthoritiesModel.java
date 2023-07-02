package backend.persist.models;

import backend.persist.entity.Permission;
import backend.persist.entity.User;
import backend.security.Role;
import backend.security.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class UserWithAuthoritiesModel {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Status status;
    private List<Permission> permissionList;

    public static UserWithAuthoritiesModel toModel(User user, List<Permission> permissions){
        UserWithAuthoritiesModel userWithAuthoritiesModel = new UserWithAuthoritiesModel();
        userWithAuthoritiesModel.setPermissionList(permissions);
        userWithAuthoritiesModel.setUserName(user.getUsername());
        userWithAuthoritiesModel.setFirstName(user.getFirstName());
        userWithAuthoritiesModel.setLastName(user.getLastName());
        userWithAuthoritiesModel.setPassword(userWithAuthoritiesModel.getPassword());
        userWithAuthoritiesModel.setRole(user.getRole());
        userWithAuthoritiesModel.setStatus(user.getStatus());

        return userWithAuthoritiesModel;
    }
}
