package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.UserRepository;
import com.ecomerceApi.Priscila.requestModels.AuthenticationRequest;
import com.ecomerceApi.Priscila.requestModels.AuthenticationResponse;
import com.ecomerceApi.Priscila.requestModels.LoginRequest;
import com.ecomerceApi.Priscila.service.AuthenticationService;
import com.ecomerceApi.Priscila.requestModels.RegisterRequest;
import com.ecomerceApi.Priscila.service.JwtService;
import com.ecomerceApi.Priscila.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@RestController
@RequestMapping("")
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
