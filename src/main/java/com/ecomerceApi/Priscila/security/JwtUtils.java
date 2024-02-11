package com.ecomerceApi.Priscila.security;

import com.ecomerceApi.Priscila.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtUtils {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    @Value("${priscila.app.jwtSecret}")
    private String jwtSecret;

    @Value("${priscila.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.ES512)
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
            logger.log(Level.SEVERE,"Invalid JWT token: ", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "Token is expired ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "Token not supported ", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Token claims empty string ", e.getMessage());
        }
        return false;
    }
}
