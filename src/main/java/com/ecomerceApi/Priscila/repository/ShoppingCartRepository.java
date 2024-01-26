package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {
    Optional<ShoppingCart>findByShoppingCartId (Long cartId);

}
