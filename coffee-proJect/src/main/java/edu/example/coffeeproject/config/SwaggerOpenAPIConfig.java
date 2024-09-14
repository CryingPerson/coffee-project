package edu.example.coffeeproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@OpenAPIDefinition(
        info = @Info(
                title = "Rest API",
                version = "ver 0.1",
                description = "RESTful API Documentaion"
        ),
        servers = {@Server(
                description = "prod ENV",
                url = "http://localhost:8080/"
        ),
                @Server(
                        description = "staging ENV",
                        url = "http://localhost:8080/staging"
                )

        }
)
@Component
public class SwaggerOpenAPIConfig {
    @Bean
    public OpenAPI api() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);
    }
}
