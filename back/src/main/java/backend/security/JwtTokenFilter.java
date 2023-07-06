package backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

/**
 * JwtTokenFilter is a component that extends the GenericFilterBean class.
 * It filters and validates JWT tokens in the incoming requests.
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;


    /**
     * Constructs a new JwtTokenFilter with the JwtTokenProvider.
     *
     * @param jwtTokenProvider the JwtTokenProvider used for token operations
     */
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Filters the incoming request, validates the JWT token, and sets the authentication in the SecurityContextHolder.
     * If the token is expired or invalid, it sends an error response.
     *
     * @param servletRequest  the ServletRequest object representing the incoming request
     * @param servletResponse the ServletResponse object representing the outgoing response
     * @param filterChain     the FilterChain object for invoking the next filter in the chain
     * @throws IOException      if an I/O error occurs during the filter process
     * @throws ServletException if a servlet-related error occurs during the filter process
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,  ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        try {

            if (token != null) {
                token = token.split(" ")[1].trim();
                jwtTokenProvider.validateToken(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
