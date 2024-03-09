package com.example.emailsgenerator.CorsConfig;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
 
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/chat")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("POST", "OPTIONS")  // Ajoutez OPTIONS ici
            .allowedMethods("GET", "OPTIONS")  // Ajoutez OPTIONS ici
            .allowedHeaders("*")
            .exposedHeaders("Authorization");
    }
}
