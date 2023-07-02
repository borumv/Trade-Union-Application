package backend.requests;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Data
public class ChangePasswordRequestr {

    @Email
    private String email;
    private String actualPassword;

    @Min(3)
    private String newPassword;
}
