package com.ecomerceApi.Priscila.dto;

import lombok.*;
import org.springframework.lang.NonNull;

// for some reason annotation @Getter is not working. So I created manually a get method
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String role;

    @NonNull
    public String getEmail() {
        return email;
    }
}