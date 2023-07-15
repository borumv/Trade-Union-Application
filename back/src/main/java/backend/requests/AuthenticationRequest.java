package backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    private String password;
}
