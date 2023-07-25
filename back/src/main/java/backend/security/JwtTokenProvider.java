package backend.security;

import backend.validator.error.ValidationError;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * JwtTokenProvider is a component that provides operations related to JWT tokens.
 *
 * @author Boris Vlasevsky
 */
@Component
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretkey;

    @Value("${jwt.header}")
    private String authorizationHeader;

    /**
     * Constructs a new JwtTokenProvider with the specified UserDetailsService.
     *
     * @param userDetailsService the UserDetailsService used for user-related operations
     */
    public JwtTokenProvider(
            @Qualifier("userDetailsServiceImpl")
            UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    /**
     * Creates a new JWT token for the specified username and role.
     *
     * @param username the username
     * @return the generated JWT token
     */
    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(30, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Validates the specified JWT token.
     *
     * @param token the JWT token to validate
     * @throws JwtException if the token is invalid or expired
     */
    public boolean isTokenValid(String token, String userDetails) throws AuthenticationException{

        try {
            final String email = extractEmail(token);
            if (( email.equals(userDetails) ) && ! isTokenExpired(token))
                return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Retrieves the authentication object for the specified JWT token.
     *
     * @param token the JWT token
     * @return the Authentication object
     */
    public Authentication getAuthentication(String token) {

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(extractEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token          the JWT token
     * @param claimsResolver the function to resolve the desired claim from the Claims object
     * @param <T>            the type of the claim value
     * @return the extracted claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws AuthenticationException {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws AuthenticationException{

              return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }

    /**
     * Extracts the email (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the extracted email
     */
    public String extractEmail(String token) throws AuthenticationException{

        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Resolves the JWT token from the Authorization header in the HttpServletRequest.
     *
     * @param request the HttpServletRequest object
     * @return the resolved JWT token
     */
    public String resolveToken(HttpServletRequest request) {

        return request.getHeader(authorizationHeader);
    }
}
