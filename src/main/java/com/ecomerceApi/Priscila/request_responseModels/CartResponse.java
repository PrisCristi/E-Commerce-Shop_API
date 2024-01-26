package com.ecomerceApi.Priscila.request_responseModels;

import com.ecomerceApi.Priscila.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
@ToString
public class CartResponse {

    private ShoppingCart shoppingCart;

    private String message;
    private List<ShoppingCart> cart;     // list of products from shoppingCart
    private BigDecimal totalPrice;

    public CartResponse(String message, List<ShoppingCart> cart) {
        this.message = message;
        this.cart = cart;

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCart shoppingCart : cart) {
            BigDecimal itemPrice = BigDecimal.valueOf(shoppingCart.getProduct().getPrice());
            BigDecimal quantity = BigDecimal.valueOf(shoppingCart.getQuantity());
            BigDecimal totalItem = itemPrice.multiply(quantity);
            totalPrice = totalPrice.add(totalItem);
        }
        this.totalPrice = totalPrice.setScale(3, RoundingMode.HALF_UP);

    }
}

