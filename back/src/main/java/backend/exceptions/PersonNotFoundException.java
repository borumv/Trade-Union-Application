package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class indicating that a specific Person instance was not found.
 * <p>
 * This exception is typically thrown when attempting to retrieve or manipulate a non-existent Person object.
 *
 * @author Boris Vlasevsky
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    /**
     * Constructs a new PersonNotFoundException with the given ID.
     *
     * @param id the ID of the Person instance that was not found
     */
    public PersonNotFoundException(Long id) {

        super("Person id not found: " + id);
    }
}