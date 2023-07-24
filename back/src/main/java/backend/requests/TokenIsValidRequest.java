package backend.requests;
import lombok.Data;

@Data
public class TokenIsValidRequest {
    private String token;
    private String email;

}
