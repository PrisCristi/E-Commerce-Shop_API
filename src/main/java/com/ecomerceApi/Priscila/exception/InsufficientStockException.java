
package com.ecomerceApi.Priscila.exception;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.model.Product;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class InsufficientStockException extends Exception {

    private final List<Product> productUnavailable = new ArrayList<>();

    public InsufficientStockException(String message) {

        super(message);
    }

    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message, List<Cart> unavailableCartItemItems) {
        super(message);
//        unavailableCartItemItems.forEach(cart -> productUnavailable.add(cart.getProduct()));
    }

    public InsufficientStockException(String message, Product product) {
        super(message);
        productUnavailable.add(product);
    }
}