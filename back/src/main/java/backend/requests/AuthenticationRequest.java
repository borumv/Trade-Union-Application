package backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    private String password;
}
