package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.security.JwtUserDetails;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService { // generate tokens

    private long jwtExpiration;
    private SecretKey jwtKey;
    private JwtParser jwtParser; // read tokens

    public JwtService(
            @Value("${application.security.jwt.expiration}") long jwtExpiration,
            @Qualifier("jwtKey") SecretKey jwtKey,
            JwtParser jwtParser) { // created a constructor and passed valeus related to secrete key, expiration and parser
        this.jwtExpiration = jwtExpiration;
        this.jwtKey = jwtKey;
        this.jwtParser = jwtParser;
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return generateToken(userDetails, extraClaims, jwtExpiration);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims, long expiration) {
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claims(extraClaims)
                .signWith(jwtKey)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return jwtParser
                .parse(token)
                .accept(Jws.CLAIMS)
                .getPayload();
    }

    public UserDetails extractUser(String jwt) {
        final Claims claims = extractAllClaims(jwt);

        @SuppressWarnings("unchecked")
        List<String> authList = claims.get("auth", List.class);

        return JwtUserDetails
                .builder()
                .username(claims.getSubject())
                .authorities(authList
                        .stream()
                        .map(o -> new SimpleGrantedAuthority(String.valueOf(o)))
                        .collect(Collectors.toList()))
                .build();
    }

}
