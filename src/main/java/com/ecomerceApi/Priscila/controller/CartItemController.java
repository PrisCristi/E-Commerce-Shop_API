package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.model.CartItem;
import com.ecomerceApi.Priscila.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: 17.02.24 Create addItemInCart, getItemFromtheCart,updateItemIntheCart, deleteItemFromtheCart
@RestController
@Validated
@RequestMapping("/api/v1/cart_items")
public class CartItemController {

    @Autowired
    private final CartItem cartItem;
    @Autowired
    private final CartItemService service;

    @Autowired
    public CartItemController(CartItem cartItem, CartItemService service) {
        this.cartItem = cartItem;
        this.service = service;
    }


    @PostMapping("/item")
    public CartItem addItemInCart(CartItem cartItem) {
        return (CartItem) service.addItemInCart(cartItem);

    }

}
