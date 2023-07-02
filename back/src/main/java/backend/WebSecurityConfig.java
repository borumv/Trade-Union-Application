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
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AuthEntryPointJwt unautorizedHadler;

    private final JwtConfigure jwtConfigure;
    private final JwtTokenFilter jwtTokenFilter;
    private final ObjectMapper om = new ObjectMapper();
    private final UserDetailsService jwtUserDetailsService;


    public WebSecurityConfig(JwtConfigure jwtConfigure, JwtTokenFilter jwtTokenFilter, @Qualifier("userDetailsServiceImpl") UserDetailsService jwtUserDetailsService) {
        this.jwtConfigure = jwtConfigure;
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()

                .authorizeRequests().antMatchers("/api/auth/login", "/api/auth/register", "/webjars/springfox-swagger-ui/", "/api/auth/", "/swagger-ui.html", "/swagger-ui.html/", "/api/user/findEmail").permitAll().and()
                .authorizeRequests()
                .antMatchers("/api/auth/fdsdf")
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unautorizedHadler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
//////                .formLogin()
////                .loginProcessingUrl("/swagger-ui.html")
//                .and()
                .apply(jwtConfigure);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                    logger.error(accessDeniedException.getMessage() + " -> " + request.getRequestURI() + " : " + auth.getName());

                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    ServletOutputStream out = response.getOutputStream();
                    om.writeValue(out, accessDeniedException.getMessage());
                    out.flush();
                });
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*");
    }
}