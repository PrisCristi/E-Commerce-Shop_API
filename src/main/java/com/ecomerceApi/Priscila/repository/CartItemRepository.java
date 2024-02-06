package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.CartItem;
import com.ecomerceApi.Priscila.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
