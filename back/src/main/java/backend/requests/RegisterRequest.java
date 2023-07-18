package backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a registration request.
 * <p>
 * This class includes the email, password, firstName, and lastName fields for user registration.
 *
 * @author Boris Vlasevsky
 */
@Data
@Builder
public class RegisterRequest {

    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    private String password;
    @Builder.Default
    private String firstName = "empty FirstName";
    @Builder.Default
    private String lastName = "empty lastName";
}
