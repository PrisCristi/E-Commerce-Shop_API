package com.ecomerceApi.Priscila.requestModels;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationRequest() {
    }
}
