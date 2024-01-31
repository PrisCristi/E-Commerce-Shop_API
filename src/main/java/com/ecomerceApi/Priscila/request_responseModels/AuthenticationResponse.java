package com.ecomerceApi.Priscila.request_responseModels;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import org.springdoc.core.configuration.oauth2.SpringDocOAuth2Token;

@Getter
@Value
@Builder
public class AuthenticationResponse implements SpringDocOAuth2Token {

    String accessToken;
    String tokenType = "Bearer";
    long expiresIn = 3600;
    String refreshToken;
    String scope;

}
