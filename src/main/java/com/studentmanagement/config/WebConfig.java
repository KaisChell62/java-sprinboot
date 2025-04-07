package com.studentmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Définit où sont les ressources statiques (CSS, JS, images)
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
                
        // Empêche Spring de traiter les routes /web/** comme des ressources statiques
        registry.addResourceHandler("/web/**")
                .addResourceLocations("classpath:/templates/")
                .setCachePeriod(0);
    }
} 