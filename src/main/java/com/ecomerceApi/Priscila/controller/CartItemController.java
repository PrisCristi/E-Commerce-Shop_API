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

    private final CartItemService service;

    @Autowired
    public CartItemController(CartItemService service) {
        this.service = service;
    }


    @PostMapping("/item")
    public CartItem addItemInCart(CartItem cartItem) {
         service.addItemInCart(cartItem);
         return cartItem;

    }

}
