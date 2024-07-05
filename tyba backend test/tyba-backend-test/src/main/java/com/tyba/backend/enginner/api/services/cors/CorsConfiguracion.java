package com.tyba.backend.enginner.api.services.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguracion implements WebMvcConfigurer {
    public void addCorsConfiguracion(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT" )
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
