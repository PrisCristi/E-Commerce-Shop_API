package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.dto.UserRegistrationRequest;
import com.ecomerceApi.Priscila.dto.UserRegistrationResponse;
import com.ecomerceApi.Priscila.exception.UserExistsExecption;
import com.ecomerceApi.Priscila.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserRegistrationResponse respnse = userService.registerUser(registrationRequest); // TODO create a registerUser method in service
            return ResponseEntity.ok(respnse);
        } catch (UserExistsExecption e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
