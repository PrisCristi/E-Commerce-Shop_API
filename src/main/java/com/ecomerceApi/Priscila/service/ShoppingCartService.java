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
import org.springframework.stereotype.Service;

import java.util.Optional;


/* needing to create a method which add products to cart.
 * needing to request products from a list(?) / from a class (?)
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository cartRepository;
    private ProductService productService;

    @Transactional
    public CartResponse addProductToCart(CartRequest cartRequest, User user) throws InsufficientStockException, ProductNotFoundException, ProductExistsException {

        long quantity = cartRequest.getQuantity();
        Product product = productService.getProductById(cartRequest.getProductId());
        Optional<ShoppingCart> foundCart = cartRepository.findByCartByCustomerAndProduct(user, product);
        if (foundCart.isPresent()) {
            throw new ProductExistsException("Product already in the cart");
        } else {
            if (product.getStockQuantity() >= cartRequest.getQuantity()) {
                cartRepository.save(new ShoppingCart(quantity, product, user));
            } else
                throw new InsufficientStockException("Insufficient product on stock", product);
        }

        return cartTotal(user);
    }

    public CartResponse cartTotal(User user) {
        return new CartResponse("Your cart, " + user.getName(), cartRepository.getCartByCustomer(user));
    }
}

