package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class indicating an error with a new password.
 * <p>
 * This exception is typically thrown when there is an issue with a newly set password.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ErrorNewPasswordException extends RuntimeException {

    /**
     * Constructs a new ErrorNewPasswordException with the given error message.
     *
     * @param message the error message indicating the issue with the new password
     */
    public ErrorNewPasswordException(String message) {

        super(message);
    }
}