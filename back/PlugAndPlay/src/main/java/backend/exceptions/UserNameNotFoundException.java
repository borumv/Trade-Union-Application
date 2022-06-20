package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException  {
    public UserNameNotFoundException(String name) {
        super("Email not found : " + name);
    }
}
