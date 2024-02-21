
package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.CartNotFoundException;
import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    // TODO: 17.02.24 Create cart and deleteCart


    private final CartService cartService;

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) throws CartNotFoundException {
        return cartService.getCartById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ROLE')")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

}