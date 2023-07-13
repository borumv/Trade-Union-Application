package backend.services;
import backend.exceptions.ErrorNewPasswordException;
import backend.exceptions.UserNameNotFoundException;
import backend.persist.entity.User;
import backend.persist.models.UserModel;
import backend.persist.repositories.PermoRepo;
import backend.persist.repositories.UserRepo;
import backend.requests.ChangePasswordRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PermoRepo permoRepo;

    @InjectMocks
    private UserService userService;


    @Test
    public void testFindByEmail_ExistingEmail_ReturnsUser() {
        String email = "test@example.com";
        User expectedUser = new User();
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findByEmail(email);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindByEmail_NonExistingEmail_ThrowsUserNameNotFoundException() {
        String email = "nonexisting@example.com";
        when(userRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNameNotFoundException.class, () -> userService.findByEmail(email));
    }

    @Test
    public void testSave() {
        User user = new User();
        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals(user, savedUser);
    }

    @Test
    public void testChangePassword_ActualPasswordMatches_NewPasswordSet() {
        String email = "test@example.com";
        String actualPassword = "oldpassword";
        String newPassword = "newpassword";
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(email, actualPassword, newPassword);
        User user = new User();
        user.setEmail(email);
        user.setPassword(actualPassword);
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);

        UserModel userModel = userService.changePassword(changePasswordRequest);

        assertEquals(newPassword, user.getPassword());
        assertNotNull(userModel);
        assertEquals(user.getEmail(), userModel.getUserName());
    }

    @Test
    public void testChangePassword_ActualPasswordDoesNotMatch_ErrorNewPasswordExceptionThrown() {
        String email = "test@example.com";
        String actualPassword = "oldpassword";
        String newPassword = "newpassword";
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(email, actualPassword, newPassword);
        User user = new User();
        user.setEmail(email);
        user.setPassword("differentpassword");
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

        assertThrows(ErrorNewPasswordException.class, () -> userService.changePassword(changePasswordRequest));
        assertNotEquals(newPassword, user.getPassword());
    }
}