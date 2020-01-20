package com.unkn0wnl.dev.notes.api.security;

import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class RestJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(RestJwtAuthenticationEntryPoint.class);
    }

    public RestJwtAuthenticationEntryPoint() {
        super();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        LOGGER.error("Responding with unauthorized error. Message - {}", authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    }
}
