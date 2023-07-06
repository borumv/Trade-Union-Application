package backend.security;
import backend.persist.entity.User;
import backend.persist.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;


/**
 * UserDetailsServiceImpl is a service class that implements the UserDetailsService interface.
 * It provides user-related operations for authentication and authorization.
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    /**
     * Constructs a new UserDetailsServiceImpl with the specified UserRepo.
     *
     * @param userRepo the UserRepo used for user-related operations
     */
    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Loads the user details for the specified email.
     *
     * @param email the email of the user
     * @return the UserDetails object for the user
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepo.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email {}" + email));
        return SecurityUser.fromUser(userEntity);

    }

    /**
     * Retrieves the Principal object.
     *
     * @param principal the Principal object
     * @return the Principal object
     */
    static Principal getPrincipal(Principal principal) {
        return principal;
    }
}
