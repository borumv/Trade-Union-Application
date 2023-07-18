package backend.services;

import backend.exceptions.ErrorNewPasswordException;
import backend.exceptions.UserNameNotFoundException;
import backend.persist.entity.User;
import backend.persist.models.UserModel;
import backend.persist.repositories.PermoRepo;
import backend.persist.repositories.UserRepo;
import backend.requests.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PermoRepo permoRepo;

    /**
     * Finds a user by email.
     *
     * @param email the email of the user to find
     * @return the found user
     * @throws UserNameNotFoundException if no user is found with the specified email
     */
    public User findByEmail(String email) throws UserNameNotFoundException {

        return userRepo.findByEmail(email).orElseThrow(() -> new UserNameNotFoundException("not found with email this - " + email));
    }

    /**
     * Saves a user.
     *
     * @param user the user to save
     * @return the saved user
     */
    public User save(User user) {

        return userRepo.save(user);
    }

    /**
     * Changes the password of a user.
     *
     * @param changePasswordRequest the change password request containing the email, actual password, and new password
     * @return the user model representing the updated user
     * @throws ErrorNewPasswordException if the actual password does not match the user's current password
     */
    public UserModel changePassword(ChangePasswordRequest changePasswordRequest) throws ErrorNewPasswordException {

        User user = findByEmail(changePasswordRequest.getEmail());
        if (changePasswordRequest.getActualPassword().equals(user.getPassword())) {
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepo.save(user);
        } else {
            throw new ErrorNewPasswordException("Password not concur");
        }
        return UserModel.toModel(user);
    }
}
