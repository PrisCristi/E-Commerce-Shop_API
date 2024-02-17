
package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    // TODO: 17.02.24 check the medothes bellow are necessary

//    Optional<CartItem> getCartByUserAndProduct(User user, Product product);
//    List<CartItem> getCartsByUser(User user);
}
