package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.exception.InsufficientStockException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.model.CartItem;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.model.User;
import com.ecomerceApi.Priscila.repository.CartItemRepository;
import com.ecomerceApi.Priscila.repository.CartRepository;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.security.JwtUserDetails;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductService productService;
    private ProductRepository productRepository;


    public Cart getMyCart() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Cart) cartRepository.getCartsByUser((User) userDetails.getAuthorities());
    }

    public Cart createCart(Long userId) {
        return cartRepository.save(new Cart(userId));
    }

    @Transactional
    public CartItem addProductToCart(CartItem cartItem, User user) throws InsufficientStockException, ProductNotFoundException {

        int quantity = cartItem.getQuantity();
        Product product = productService.getProductById(cartItem.getProductId());

        Optional<CartItem> foundCart = cartRepository.getCartByUserAndProduct(user, product);
        if (product.getStockQuantity() >= cartItem.getQuantity()) {
            CartItem itemToSave = new CartItem();
            if (foundCart.isPresent()) {
                itemToSave.setQuantity((itemToSave.getQuantity() + cartItem.getQuantity()));
            } else {
                itemToSave = cartItem;
                itemToSave.setUser(user);
                itemToSave.setProduct(product);
            }
            return cartItemRepository.save(itemToSave);
        }
        return cartItem;
    }

    @Transactional
    public CartItem updateProductInCart(CartItem cartItem, User user) throws InsufficientStockException, ProductNotFoundException {

        int newQuantity = cartItem.getQuantity();
        Product product = productRepository.findById(
                        cartItem.getProduct().getProductId())
                .orElseThrow();

        Optional<CartItem> foundCart = cartRepository.getCartByUserAndProduct(user, product);
        if (foundCart.isEmpty()) {
            throw new ProductNotFoundException("Product not founded");
        }

        CartItem updatedItem = foundCart.get();
        if (product.getStockQuantity() >= newQuantity) {
            updatedItem.setQuantity(newQuantity);

        } else {
            updatedItem = cartItem;
            updatedItem.setUser(user);
            updatedItem.setProduct(product);

            return cartItemRepository.save(updatedItem);

        }
        return cartItem;
    }

}


