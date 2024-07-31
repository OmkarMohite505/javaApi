package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static content from /ui folder
        registry.addResourceHandler("/ui/**")
                .addResourceLocations("classpath:/static/ui/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward all requests under /ui to /ui/index.html
        registry.addViewController("/ui/**").setViewName("forward:/ui/index.html");
    }
}

