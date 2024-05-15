package br.com.lgmanagement.lgManagement.config.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "tokenAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        security = {
                @SecurityRequirement(name = "tokenAuth")
        }
)
public class OpenApiConfig {
}
