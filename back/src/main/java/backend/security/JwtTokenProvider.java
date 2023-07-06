package backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

/**
 * JwtTokenProvider is a component that provides operations related to JWT tokens.
 */
@Component
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
    public JwtTokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * Creates a new JWT token for the specified username and role.
     *
     * @param username the username
     * @param role     the role
     * @return the generated JWT token
     */
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
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
    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
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
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
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
    public String extractEmail(String token) {
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
