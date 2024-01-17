package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// created to monitor app.

@RestController
@RequestMapping ("/check")
public class HealthCheckController {

    UserRepository userRepository;

    public HealthCheckController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<Void> check(){
        userRepository.findAll(); // return a list with all users
        return ResponseEntity.ok().build();
    }

}
