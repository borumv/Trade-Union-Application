package backend.security;

import backend.persist.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

/**
 * SecurityUser is a class that implements the UserDetails interface.
 * It represents a user for security purposes.
 */
@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    /**
     * Retrieves the user's authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Retrieves the user's password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * Retrieves the user's username.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the user's account is not expired.
     *
     * @return true if the account is not expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    /**
     * Checks if the user's account is not locked.
     *
     * @return true if the account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    /**
     * Checks if the user's credentials are not expired.
     *
     * @return true if the credentials are not expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    /**
     * Checks if the user is enabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return isActive;
    }

    /**
     * Creates a SecurityUser object from the specified User object.
     *
     * @param user the User object
     * @return the created SecurityUser object
     */
    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder()
                .encode(user.getPassword()),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities());
    }
}
