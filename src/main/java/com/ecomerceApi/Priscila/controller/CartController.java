package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.request_responseModels.CartResponse;
import com.ecomerceApi.Priscila.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private CartService cartService;


    @PostMapping("/mycart/add")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CartResponse> addProductToMyCart(@RequestParam("productId") long productId,
                                                           @RequestParam("quantity") int quantity) throws ProductNotFoundException {
        //cartService.addProductToCart(productId, quantity);
       // return ResponseEntity.ok().body(new CartResponse("Added product to your cart"));
    return null;
    }
}
