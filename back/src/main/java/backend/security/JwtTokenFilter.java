package backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * JwtTokenFilter is a component that extends the GenericFilterBean class.
 * It filters and validates JWT tokens in the incoming requests.
 *
 * @author Boris Vlasevsky
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new JwtTokenFilter with the JwtTokenProvider.
     *
     * @param jwtTokenProvider   the JwtTokenProvider used for token operations
     * @param userDetailsService
     */
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider,
                          @Qualifier("userDetailsServiceImpl")
                          UserDetailsService userDetailsService) {

        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
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
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final String authHeader = ( (HttpServletRequest) servletRequest ).getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || ! authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtTokenProvider.extractEmail(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //Get our users data from db
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            //check if the user is valid or not
            if (jwtTokenProvider.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) servletRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
