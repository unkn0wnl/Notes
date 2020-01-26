package com.unkn0wnl.dev.notes.api.config.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.BaseUri;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class DataRestMvcConfig extends RepositoryRestMvcConfiguration {

    @Value("${app.base_path}")
    private String basePath;

    @Override
    @Bean
    public BaseUri baseUri() {
        config().setBasePath(basePath);
        return new BaseUri(config().getBaseUri());
    }

}
