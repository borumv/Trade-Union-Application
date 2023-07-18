package backend.persist.models;

import backend.persist.entity.User;
import backend.security.Role;
import backend.security.Status;
import lombok.Data;

/**
 * Model class representing a User.
 * <p>
 * This class includes attributes for the userName, firstName, lastName, password, role, and status.
 */
@Data
public class UserModel {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Status status;

    /**
     * Converts a User object to a UserModel object.
     *
     * @param user the User object to be converted
     * @return the corresponding UserModel object
     */
    public static UserModel toModel(User user) {

        UserModel userModel = new UserModel();
        userModel.setUserName(user.getEmail());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPassword(user.getPassword());
        userModel.setRole(user.getRole());
        userModel.setStatus(user.getStatus());
        return userModel;
    }
}