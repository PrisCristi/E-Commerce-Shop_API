package com.ecomerceApi.Priscila.requestModels;

import com.ecomerceApi.Priscila.model.Role;
import lombok.*;
import org.springframework.lang.NonNull;

@Data
public class UserRegistrationRequest {

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Role role;

}