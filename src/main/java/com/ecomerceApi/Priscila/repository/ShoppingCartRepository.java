package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.model.ShoppingCart;
import com.ecomerceApi.Priscila.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

    Optional<ShoppingCart>findByCartByCustomerAndProduct (User user, Product product);
    List<ShoppingCart>getCartByCustomer(User user);

}
