package es.atsistemas.heromaker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration

@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)

@OpenAPIDefinition(
        info = @Info(
                title = "Hero Maker",
                description = "API REST"
        ),
        security = {@SecurityRequirement(name = "JWT")}
)
public class SwaggerConfig {
}
