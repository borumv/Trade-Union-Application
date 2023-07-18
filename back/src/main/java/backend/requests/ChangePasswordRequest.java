package backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a change password request.
 * <p>
 * This class includes the email, actualPassword, and newPassword fields for changing the password.
 *
 * @author Boris Vlasevsky
 */
@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    @Email
    private String email;
    private String actualPassword;

    @Min(3)
    private String newPassword;
}
