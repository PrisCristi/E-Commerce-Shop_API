package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.requestModels.AuthenticationResponse;
import com.ecomerceApi.Priscila.requestModels.LoginRequest;
import com.ecomerceApi.Priscila.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {  // authenticate Login access.

    private UserService userService;
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse>login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(AuthenticationResponse.builder()
                .accessToken(userService.login(loginRequest.getUsername(),loginRequest.getPassword()))  // create meth in UserService
                .build(),
                HttpStatus.OK);
    }


}
