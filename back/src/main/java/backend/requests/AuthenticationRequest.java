package backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * Represents an authentication request.
 * <p>
 * This class includes the email and password fields for authentication.
 *
 * @author Boris Vlasevsky
 */
@Data
@Builder
public class AuthenticationRequest {

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    private String password;
}
