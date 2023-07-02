package backend.validator.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReportingServiceException extends Exception {

    private static final long serialVersionUID = 3L;

    private HttpStatus httpStatus;
    private String errorMessage;

    public ReportingServiceException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

}