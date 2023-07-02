package backend.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticationRequest {

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    private String password;
}
