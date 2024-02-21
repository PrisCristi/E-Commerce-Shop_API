package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long id);

}
