package backend;
import backend.security.JwtTokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());
    private final JwtTokenFilter jwtTokenFilter;
    private final ObjectMapper om = new ObjectMapper();
    private final AuthenticationProvider authenticationProvider;

    /**
     * Constructs a new WebSecurityConfig with the specified dependencies.
     *
     * @param jwtTokenFilter         the JwtTokenFilter object
     * @param authenticationProvider the AuthenticationProvider object
     */
    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter, AuthenticationProvider authenticationProvider) {

        this.jwtTokenFilter = jwtTokenFilter;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object
     * @return the SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/api/auth/**", "/webjars/springfox-swagger-ui/", "/api/auth/", "/swagger-ui/index.html", "/swagger-ui.html/").permitAll().anyRequest()
                        .authenticated()
                ).sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    /**
     * Configures the web security.
     *
     * @return the WebSecurityCustomizer object
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().requestMatchers("/api/auth/login", "/api/auth/register", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/api/user/findEmail");
    }

}