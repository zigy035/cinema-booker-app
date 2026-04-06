package com.cinema.booker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cinemaBookerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema Booker API")
                        .description("REST API for booking cinema tickets")
                        .version("1.0.0"));
    }
}