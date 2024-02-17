package com.ecomerceApi.Priscila.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess(){
        return "Public content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole ('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    public String userAccess(){
        return "User content.";
    }
    @GetMapping ("/costumer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String costumerAccess(){
        return "Costumer Board";
    }
    @GetMapping ("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess(){
        return "Admin Board";
    }
}
