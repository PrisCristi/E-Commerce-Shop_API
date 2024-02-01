package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.CartRepository;
import com.ecomerceApi.Priscila.security.JwtUserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public Cart getMyCart() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartRepository.findByUserId((User) userDetails.getAuthorities()).get();
    }

    public Cart createCart(Long userId) {
        return cartRepository.save(new Cart(userId));
    }

}


