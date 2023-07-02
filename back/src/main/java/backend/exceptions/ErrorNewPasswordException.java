package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ErrorNewPasswordException extends RuntimeException {
    public ErrorNewPasswordException(String message){
        super(message);
    }
}
