
package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.model.CartItem;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<CartItem> getCartByUserAndProduct(User user, Product product);
    List<CartItem> getCartsByUser(User user);


}
