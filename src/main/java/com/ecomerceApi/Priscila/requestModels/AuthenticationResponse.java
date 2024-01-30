package com.ecomerceApi.Priscila.requestModels;

import lombok.Builder;
import lombok.Value;
import org.springdoc.core.configuration.oauth2.SpringDocOAuth2Token;

@Builder
@Value
public class AuthenticationResponse implements SpringDocOAuth2Token {

    String accessToken;
    String tokenType = "Bearer";
    long expiresIn = 3600;
    String refreshToken;
    String scope;

}
