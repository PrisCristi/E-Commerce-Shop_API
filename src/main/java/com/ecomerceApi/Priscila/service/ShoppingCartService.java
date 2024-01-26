package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.exception.InsufficientStockException;
import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.model.ShoppingCart;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.ShoppingCartRepository;
import com.ecomerceApi.Priscila.request_responseModels.CartRequest;
import com.ecomerceApi.Priscila.request_responseModels.CartResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/* needing to create a method which add products to cart.
 * needing to request products from a list(?) / from a class (?)
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository cartRepository;
    private ProductService productService; // need to interact to Product logic

    @Transactional
    public CartResponse addProductToCart(CartRequest cartRequest, User user)