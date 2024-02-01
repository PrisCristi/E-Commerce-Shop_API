package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.repository.CartRepository;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {


    private ProductRepository productRepository;

    private CartRepository cartRepository;


}


