package backend.validator.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * ReportingServiceException is an exception class that represents an error in the reporting service.
 *
 * @author Boris Vlasevsky
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportingServiceException extends Exception {

    private static final long serialVersionUID = 3L;

    private HttpStatus httpStatus; // The HTTP status associated with the exception
    private String errorMessage; // The error message

    /**
     * Constructs a new ReportingServiceException with the specified HTTP status and error message.
     *
     * @param httpStatus   The HTTP status associated with the exception
     * @param errorMessage The error message
     */
    public ReportingServiceException(HttpStatus httpStatus, String errorMessage) {

        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

}