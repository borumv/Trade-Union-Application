package backend.persist.models;

import backend.persist.entity.Permission;
import backend.persist.entity.User;
import backend.security.Role;
import backend.security.Status;
import lombok.Data;

import java.util.List;

/**
 * Model class representing a User with authorities.
 * <p>
 * This class includes attributes for the userName, firstName, lastName, password, role, status, and a list of permissions.
 */
@Data
public class UserWithAuthoritiesModel {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Status status;
    private List<Permission> permissionList;

    /**
     * Converts a User object and a list of permissions to a UserWithAuthoritiesModel object.
     *
     * @param user        the User object to be converted
     * @param permissions the list of permissions associated with the user
     * @return the corresponding UserWithAuthoritiesModel object
     */
    public static UserWithAuthoritiesModel toModel(User user, List<Permission> permissions) {

        UserWithAuthoritiesModel userWithAuthoritiesModel = new UserWithAuthoritiesModel();
        userWithAuthoritiesModel.setPermissionList(permissions);
        userWithAuthoritiesModel.setUserName(user.getEmail());
        userWithAuthoritiesModel.setFirstName(user.getFirstName());
        userWithAuthoritiesModel.setLastName(user.getLastName());
        userWithAuthoritiesModel.setPassword(user.getPassword());
        userWithAuthoritiesModel.setRole(user.getRole());
        userWithAuthoritiesModel.setStatus(user.getStatus());
        return userWithAuthoritiesModel;
    }
}