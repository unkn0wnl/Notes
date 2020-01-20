package com.l.dev.config.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

@Configuration
public class RestJpaServiceApplicationContextConfig extends RepositoryRestConfigurerAdapter {

    @Value("${app.base_path}")
    private String basePath;

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath(basePath);
                config.setReturnBodyOnCreate(Boolean.TRUE);
                config.setReturnBodyOnUpdate(Boolean.TRUE);
                config.useHalAsDefaultJsonMediaType(false);
                config.setDefaultMediaType(MediaType.APPLICATION_JSON);
            }
        };
    }
}
