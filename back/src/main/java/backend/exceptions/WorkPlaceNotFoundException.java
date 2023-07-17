package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class indicating that a specific WorkPlace instance was not found.
 * <p>
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent WorkPlace object.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WorkPlaceNotFoundException extends RuntimeException {

    /**
     * Constructs a new WorkPlaceNotFoundException with the given ID.
     *
     * @param id the ID of the WorkPlace instance that was not found
     */
    public WorkPlaceNotFoundException(int id) {

        super("WorkPlace id not found: " + id);
    }
}

