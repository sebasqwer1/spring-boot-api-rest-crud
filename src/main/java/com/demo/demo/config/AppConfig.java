package com.demo.demo.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info().description("Demostración de una api rest con Spring boot, Oracle y Swagger v3.").title("API REST SPRING BOOT").version("v1.0.0");
        return new OpenAPI().info(info);
    }
}
