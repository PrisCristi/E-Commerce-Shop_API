package com.ecomerceApi.Priscila.requestModels;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Value
public class LoginRequest {

    String username;
    String password;
}
