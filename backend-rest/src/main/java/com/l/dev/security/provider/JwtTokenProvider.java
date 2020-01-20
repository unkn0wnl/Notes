package com.l.dev.security.provider;

import com.l.dev.security.principal.UserPrincipal;
import com.l.dev.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class JwtTokenProvider implements TokenProvider {

    public static final int JWT_TOKEN_START_INDEX = 7;

    public static final String JWT_TOKEN_TYPE = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(JwtTokenProvider.class);
    }

    @Value("${app.jwt.jwtSecret}")
    private String jwtSecrete;
    @Value("${app.jwt.jwtExpirationMs}")
    private Integer jwtExpirationMs;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtTokenProvider() {
        super();
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        LOGGER.info(userPrincipal.toString());
        return Jwts.builder()
                .setSubject(userPrincipal.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecrete)
                .compact();
    }

    private Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecrete)
                .parseClaimsJws(token)
                .getBody();

        String strUserId = claims.getSubject();
        Long userId = null;

        try {
            userId = Long.parseLong(strUserId);
        } catch (NumberFormatException | NullPointerException ex) {
            LOGGER.error(ex);
        }
        return userId;
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(HEADER_AUTHORIZATION);
        LOGGER.info("Bearer Token: {}", bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_TOKEN_TYPE)) {
            return bearerToken.substring(JWT_TOKEN_START_INDEX);
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwtToken) {
        Long userId = this.getUserIdFromJwt(jwtToken);
        UserDetails userDetails = ((UserDetailsServiceImpl) userDetailsService).loadUserById(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public boolean validateToken(String authToken) {
        boolean validationResult = true;

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecrete).parseClaimsJws(authToken);

            if (claims.getBody().getExpiration().before(new Date())) {
                validationResult = false;
            }
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature. {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token. {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token. {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token. {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty. {}", ex.getMessage());
        }
        return validationResult;
    }
}