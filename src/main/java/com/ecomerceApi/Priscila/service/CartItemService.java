package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartRepository repository;
    private final UserDetailsServiceImpl userService;

    public Cart addItemInCart(Cart cart){
        return addItemInCart(new Cart());
    }
}
