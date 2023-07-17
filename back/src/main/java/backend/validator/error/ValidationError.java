package backend.validator.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ValidationError represents an error in the validation process.
 *
 * @author Boris Vlasevsky
 */
@Data
@AllArgsConstructor
public class ValidationError {

    public String message, path, type = "error";
}