package backend.security;

import backend.validator.error.ValidationError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * JwtAuthenticationException is an exception class that extends AuthenticationException.
 * It represents an exception related to JWT authentication.
 *
 * @author Boris Vlasevsky
 */
@Getter
public class AuthenticationException extends org.springframework.security.core.AuthenticationException {

    private HttpStatus httpStatus;
    private List<ValidationError> errors;

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message and cause.
     *
     * @param msg the detail message
     * @param t   the cause of the exception
     */
    public AuthenticationException(String msg, Throwable t) {

        super(msg, t);
    }

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message and HTTP status.
     *
     * @param msg        the detail message
     * @param httpStatus the HTTP status associated with the exception
     */
    public AuthenticationException(String msg, HttpStatus httpStatus, List<ValidationError> errors) {

        super(msg);
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message.
     *
     * @param s the detail message
     */
    public AuthenticationException(String s) {

        super(s);
    }
}
