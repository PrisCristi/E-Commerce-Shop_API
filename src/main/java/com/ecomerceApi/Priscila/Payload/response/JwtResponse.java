package com.ecomerceApi.Priscila.Payload.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public class JwtResponse {

    private String accessToken;
    private String type = "Bearer";
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken,Long id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    // TODO: 15.02.24 it seems these methods are not been used. Delete them later.

/*
    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }



    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

 */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getRoles() {
        return roles;
    }

 */

}
