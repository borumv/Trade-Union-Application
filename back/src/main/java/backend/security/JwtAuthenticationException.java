package backend.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * JwtAuthenticationException is an exception class that extends AuthenticationException.
 * It represents an exception related to JWT authentication.
 */
@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message and cause.
     *
     * @param msg the detail message
     * @param t   the cause of the exception
     */
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message and HTTP status.
     *
     * @param msg         the detail message
     * @param httpStatus  the HTTP status associated with the exception
     */
    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    /**
     * Constructs a new JwtAuthenticationException with the specified detail message.
     *
     * @param s the detail message
     */
    public JwtAuthenticationException(String s) {
        super(s);
    }
}
