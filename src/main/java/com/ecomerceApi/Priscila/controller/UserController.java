package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.dto.UserRegistrationRequest;
import com.ecomerceApi.Priscila.dto.UserRegistrationRespnse;
import com.ecomerceApi.Priscila.exception.DuplicateUserExecption;
import com.ecomerceApi.Priscila.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/user")
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    public UserController (UserService userService){
        this.userService = userService;
    }
    @PostMapping (value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json", "text/xml"})
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest){
        try {
            UserRegistrationRespnse respnse = userService.registerUser(registrationRequest); // TODO create a registerUser method
            return ResponseEntity.ok(respnse);
        } catch (DuplicateUserExecption e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
