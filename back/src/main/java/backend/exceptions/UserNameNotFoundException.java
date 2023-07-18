package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class indicating that a specific user name or email was not found.
 * <p>
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent user based on their name or email.
 *
 * @author Boris Vlasevsky
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNameNotFoundException with the given name.
     *
     * @param name the user name or email that was not found
     */
    public UserNameNotFoundException(String name) {

        super("Email not found: " + name);
    }
}