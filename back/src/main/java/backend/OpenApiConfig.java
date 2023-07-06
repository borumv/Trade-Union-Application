package backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1",
        contact = @Contact(name = "Boris", email = "java4borisa@gmail.com")),
        security = {@SecurityRequirement(name = "bearerAuth")}
)
@SecuritySchemes({
        @SecurityScheme(
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                type = SecuritySchemeType.HTTP,
                in = SecuritySchemeIn.HEADER
        ),
})
public class OpenApiConfig {
}