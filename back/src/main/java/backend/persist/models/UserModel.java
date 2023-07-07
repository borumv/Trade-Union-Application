package backend.persist.models;

import backend.persist.entity.User;
import backend.security.Role;
import backend.security.Status;
import lombok.Data;

@Data
public class UserModel {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Status status;


    public static UserModel toModel(User user){
        UserModel userWithAuthoritiesModel = new UserModel();

        userWithAuthoritiesModel.setUserName(user.getEmail());
        userWithAuthoritiesModel.setFirstName(user.getFirstName());
        userWithAuthoritiesModel.setLastName(user.getLastName());
        userWithAuthoritiesModel.setPassword(userWithAuthoritiesModel.getPassword());
        userWithAuthoritiesModel.setRole(user.getRole());
        userWithAuthoritiesModel.setStatus(user.getStatus());

        return userWithAuthoritiesModel;
    }
}
