package backend;

import backend.security.AuthEntryPointJwt;
import backend.security.JwtConfigure;
import backend.security.JwtTokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AuthEntryPointJwt unautorizedHadler;

    private final JwtConfigure jwtConfigure;
    private final JwtTokenFilter jwtTokenFilter;
    private final ObjectMapper om = new ObjectMapper();
    private final UserDetailsService jwtUserDetailsService;


    /**
     * Constructs a new WebSecurityConfig with the specified dependencies.
     *
     * @param jwtConfigure          the JwtConfigure object
     * @param jwtTokenFilter        the JwtTokenFilter object
     * @param jwtUserDetailsService the UserDetailsService object
     */
    public WebSecurityConfig(JwtConfigure jwtConfigure, JwtTokenFilter jwtTokenFilter, @Qualifier("userDetailsServiceImpl") UserDetailsService jwtUserDetailsService) {
        this.jwtConfigure = jwtConfigure;
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtUserDetailsService = jwtUserDetailsService;
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
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/api/auth/**", "/webjars/springfox-swagger-ui/", "/api/auth/", "/swagger-ui/index.html", "/swagger-ui.html/")
                                .permitAll()
                                .anyRequest()

                                .authenticated()

                )

                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .apply(jwtConfigure)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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

    /**
     * Retrieves the AuthenticationManager.
     *
     * @param config the AuthenticationConfiguration object
     * @return the AuthenticationManager
     * @throws Exception if an error occurs while retrieving the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Retrieves the PasswordEncoder.
     *
     * @return the PasswordEncoder
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the AuthenticationManagerBuilder.
     *
     * @param authenticationManagerBuilder the AuthenticationManagerBuilder
     * @throws Exception if an error occurs during configuration
     */
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }
}