package com.ecomerceApi.Priscila.service;

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
import java.util.Map;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${priscila.security.jwtSecret}")
    private String jwtSecret;
    @Value("${priscila.security.jwtExpiration}")
    private String jwtExpiration;
    @Value("${priscila.security.jwtRefreshExpirationDate}")
    private String jwtRefreshExpirationDate;


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((new Date(System.currentTimeMillis())) + jwtExpiration))
                .signWith(key(), SignatureAlgorithm.ES512)
                .compact();
    }

    public String generateRefreshToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationDate))
                .signWith(SignatureAlgorithm.ES512,jwtSecret).compact();
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



    /*
    @Value("${priscila.security.jwtSecret}")
    private String jwtSecret;
    @Value("${priscila.security.jwtExpirationMS}")
    private String jwtExpiration;
    @Value("${priscila.security.jwtRefreshExpirationDate}")
    private String jwtRefreshExpirationDate;

  /*  
    public String extractUserName (String token){   // extract username from a Jwt token
        return extractClaim(token, Claims::getSubject);
    }

    //extracts a specific claim (reividicacoes) from a JWT token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // TODO: 15.02.24 comment this method
    public String generateToken(UserDetailsImpl userDetails) { // generate token
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetailsImpl userDetails
    ) {
        return buildToken(
                extraClaims,
                userDetails,
                jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(
                new HashMap<>(),
                userDetails,
                jwtRefreshExpirationDate);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            String expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


*/





/*
    public String generateRefreshToken(Authentication authentication){
        UserDetailsImpl userRefresh = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userRefresh.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationDateInMs))
                .signWith(key(), SignatureAlgorithm.ES512)
                .compact();
    }




    public String generateRefreshToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationDateInMs))
                .signWith(SignatureAlgorithm.ES512,jwtSecret).compact();
    }

 */



}
