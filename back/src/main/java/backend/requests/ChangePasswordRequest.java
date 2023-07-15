package backend.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    @Email
    private String email;
    private String actualPassword;

    @Min(3)
    private String newPassword;
}
