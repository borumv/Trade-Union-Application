package backend.validator.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * ErrorResponse is a data class that represents an error response.
 * <p>
 * It contains information about the error message, status, timestamp, error code, and path.
 *
 * @author Boris Vlasevsky
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message; // The error message
    private Integer status; // The HTTP status code of the error response
    private ZonedDateTime timeStamp; // The timestamp when the error occurred
    private String errorCode; // The error code or type
    private String path; // The path or URL where the error occurred

}
