package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class indicating that a specific ClassEducation instance was not found.
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent ClassEducation object.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClassEducationNotFoundException extends RuntimeException {

    /**
     * Constructs a new ClassEducationNotFoundException with the given ID.
     *
     * @param id the ID of the ClassEducation instance that was not found
     */
    public ClassEducationNotFoundException(int id) {

        super("ClassEducation id not found: " + id);
    }
}