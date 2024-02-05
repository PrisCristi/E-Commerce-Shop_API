package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.exception.UserNotFoundException;
import com.ecomerceApi.Priscila.request_responseModels.CartResponse;
import com.ecomerceApi.Priscila.service.CartService;
import com.ecomerceApi.Priscila.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private  CartService cartService;
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails principal) {
        try {
            return ResponseEntity.ok(cartService.getCartTotal(String.valueOf(userService.getUserByEmail(principal.getUsername()))));
        } catch (UserNotFoundException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
