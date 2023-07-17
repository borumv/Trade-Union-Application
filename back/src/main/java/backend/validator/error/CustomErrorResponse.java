package backend.validator.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * CustomErrorResponse is a data class that represents a custom error response.
 * <p>
 * It contains information about the status, error, message, and path of the error.
 *
 * @author Boris Vlasevsky
 */
@Data
@AllArgsConstructor
@Builder
public class CustomErrorResponse {

    private int status; // The HTTP status code of the error response
    private String error; // The error type or code
    private String message; // The error message
    private String path; // The path or URL where the error occurred

    public CustomErrorResponse() {

    }
}
