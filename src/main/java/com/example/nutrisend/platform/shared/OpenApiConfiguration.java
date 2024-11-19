package com.example.nutrisend.platform.shared;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI villaSystemOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://backend-villasystem-production-36d5.up.railway.app")) // URL con HTTPS
                .info(new Info()
                        .title("Villa System REST API Documentation")
                        .description("This is the REST API documentation for the Villa System application using springdoc-openapi and OpenAPI 3.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://opensource.org")));
    }
}