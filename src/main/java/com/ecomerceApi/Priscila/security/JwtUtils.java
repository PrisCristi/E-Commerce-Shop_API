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

    @Value("${priscila.security.jwtExpirationMS}")
    private String jwtExpirationMs;

    @Value("${jwtRefreshExpirationDateInMs}")
    private int jwtRefreshExpirationDateInMs;


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date(System.currentTimeMillis()))+ jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.ES512)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication){
        UserDetailsImpl userRefresh = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userRefresh.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationDateInMs))
                .signWith(key(), SignatureAlgorithm.ES512)
                .compact();
    }


    /*
    public String generateRefreshToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationDateInMs))
                .signWith(SignatureAlgorithm.ES512,jwtSecret).compact();
    }

     */

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
            logger.error( "Token is expired ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error( "Token not supported ", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token claims empty string ", e.getMessage());
        }
        return false;
    }
}
