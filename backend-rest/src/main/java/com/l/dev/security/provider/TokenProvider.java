package com.l.dev.security.provider;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String createToken(Authentication authentication);

    String getTokenFromRequest(HttpServletRequest request);

    Authentication getAuthentication(String token);

    boolean validateToken(String authToken);
}
