package com.ecomerceApi.Priscila.security;

import com.ecomerceApi.Priscila.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${priscila.security.jwtSecret}")
    private String jwtSecret;
    @Value("${priscila.security.jwtExpiration}")
    private int jwtExpiration;

//    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor((Decoders.BASE64.decode(jwtSecret)));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: ", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token is expired ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token not supported ", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token claims empty string ", e.getMessage());
        }
        return false;
    }
}
