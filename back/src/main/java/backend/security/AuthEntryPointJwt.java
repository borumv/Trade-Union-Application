package backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthEntryPointJwt is a component that implements the AuthenticationEntryPoint interface.
 * It handles unauthorized access and sends an error response.
 *
 * @author Boris Vlasevsky
 */
@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    /**
     * Called when a user attempts to access a secured resource without proper authentication.
     * Sends an unauthorized error response with an appropriate message.
     *
     * @param httpServletRequest  the HttpServletRequest object representing the incoming request
     * @param httpServletResponse the HttpServletResponse object representing the outgoing response
     * @param e                   the AuthenticationException object representing the exception thrown during authentication
     * @throws IOException if an I/O error occurs while sending the response
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {

        log.error("Unauthorized error in commence method in AuthEntryPointJwt: {}" + e.getMessage());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getOutputStream().println("{ \"error\": \"" + e.getMessage() + "\" }");
    }
}
