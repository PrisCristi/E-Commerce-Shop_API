package com.ecomerceApi.Priscila.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    private Long userId;
    private String name;
    private String email;
    private String role;
}
