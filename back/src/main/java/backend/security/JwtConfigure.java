package backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * JwtConfigure is a component that extends the SecurityConfigurerAdapter class.
 * It configures the JwtTokenFilter to be applied before the UsernamePasswordAuthenticationFilter in the HttpSecurity.
 */
@Component
@Slf4j
public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    JwtTokenFilter jwtTokenFilter;

    /**
     * Constructs a new JwtConfigure with the JwtTokenFilter.
     *
     * @param jwtTokenFilter the JwtTokenFilter to be configured
     */
    public JwtConfigure(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    /**
     * Configures the HttpSecurity to add the JwtTokenFilter before the UsernamePasswordAuthenticationFilter.
     *
     * @param httpSecurity the HttpSecurity object to be configured
     */
    @Override
    public void configure(HttpSecurity httpSecurity) {
        log.info("method method call in JwtConfigure call" + httpSecurity.toString());
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
