package com.unkn0wnl.dev.notes.api.security.filter;

import com.unkn0wnl.dev.notes.api.security.provider.TokenProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(JwtAuthenticationFilter.class);
    }

    @Autowired
    private TokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter() {
        super();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtTokenProvider.getTokenFromRequest(httpServletRequest);
        LOGGER.info("JWT: {}", jwt);
        try {
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        (UsernamePasswordAuthenticationToken) jwtTokenProvider.getAuthentication(jwt);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            LOGGER.error("Cloud not set user authentication in security context", ex);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}