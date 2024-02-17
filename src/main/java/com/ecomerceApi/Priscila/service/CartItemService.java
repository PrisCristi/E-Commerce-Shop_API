package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.repository.CartRepository;

public class CartItemService {

    private final CartRepository repository;
    private final UserDetailsServiceImpl userService;

    public CartItemService(CartRepository repository, UserDetailsServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Cart addItemInCart(Cart cart){
        return addItemInCart(new Cart());
    }
}
