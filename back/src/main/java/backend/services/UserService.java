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

    public User findByEmail(String email) {

        return userRepo.findByEmail(email).orElseThrow(() -> new UserNameNotFoundException("not found with email this - " + email));
    }

    public User save(User user) {

        return userRepo.save(user);
    }

    public UserModel changePassword(ChangePasswordRequest changePasswordRequest) {

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
