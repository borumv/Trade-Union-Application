package backend;
import backend.exceptions.FilterChainExceptionHandler;
import backend.security.AuthEntryPointJwt;
import backend.security.JwtTokenFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    private static final Long MAX_AGE=3600L;
    private final JwtTokenFilter jwtTokenFilter;

    private FilterChainExceptionHandler filterChainExceptionHandler;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Constructs a new WebSecurityConfig with the specified dependencies.
     *
     * @param jwtTokenFilter              the JwtTokenFilter object
     * @param filterChainExceptionHandler
     * @param authenticationProvider      the AuthenticationProvider object
     */
    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter, FilterChainExceptionHandler filterChainExceptionHandler, AuthenticationProvider authenticationProvider) {

        this.jwtTokenFilter = jwtTokenFilter;
        this.filterChainExceptionHandler = filterChainExceptionHandler;
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

        return http.csrf(AbstractHttpConfigurer::disable)
                .cors()
                .and()
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/api/auth/**", "/webjars/springfox-swagger-ui/", "/api/auth/", "/swagger-ui/index.html", "/swagger-ui.html/")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new AuthEntryPointJwt())
                .and()
                .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .build();
    }

    /**
     * Configures the web security.
     *
     * @return the WebSecurityCustomizer object
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                .requestMatchers("/api/auth/login",
                                 "/api/auth/register","/api/auth/validate",
                                 "/api/auth/validate/refreshtoken", "/v3/api-docs/**",
                                 "/swagger-ui/**", "/swagger-resources/**",
                                 "/v3/api-docs/**", "/api/user/findEmail")
                .and()
                .debug(webSecurityDebug);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedHeaders(
                        HttpHeaders.AUTHORIZATION,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name())
                .maxAge(MAX_AGE)
                .allowedOrigins("http://localhost:3000")
                .allowCredentials(false);
    }




}