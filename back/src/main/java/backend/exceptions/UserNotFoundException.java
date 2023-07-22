package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new WorkPlaceNotFoundException with the given ID.
     *
     * @param id the ID of the WorkPlace instance that was not found
     */
    public UserNotFoundException(Long id) {

        super("User id not found: " + id);
    }
}
