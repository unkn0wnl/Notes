package com.unkn0wnl.dev.notes.api.config.security;

import com.unkn0wnl.dev.notes.api.config.application.DataRestMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@Import(DataRestMvcConfig.class)
public class CorsWebMvcConfig extends WebMvcConfigurerAdapter {

    public static final long MAX_AGE_SECS = 3600;

    public CorsWebMvcConfig() {
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
