package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.requestModels.AuthenticationRequest;
import com.ecomerceApi.Priscila.requestModels.AuthenticationResponse;
import com.ecomerceApi.Priscila.requestModels.LoginRequest;
import com.ecomerceApi.Priscila.service.AuthenticationService;
import com.ecomerceApi.Priscila.requestModels.RegisterRequest;
import com.ecomerceApi.Priscila.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final AuthenticationManager authenticationManager;
    private final LoginRequest loginRequest;
    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtService.generateToken(authentication);
        return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
