package backend.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ChangePasswordRequestr {

    @Email
    private String email;
    private String actualPassword;

    @Min(3)
    private String newPassword;
}
