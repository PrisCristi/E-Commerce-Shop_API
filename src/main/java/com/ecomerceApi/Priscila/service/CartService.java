package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }


/*
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

    public CartTotalResponse getCartTotal(String userEmail) throws UserNotFoundException {
        User user = userService.getUserByEmail(userEmail);
        List<CartItem> cartItems = cartRepository.getCartsByUser(user);

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            BigDecimal totalOfItems = item.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(totalOfItems);
        }

        return new CartTotalResponse(cartItems, total);
    }

    @Transactional
    public CartItem updateProduct(CartItem cartItem, User user) throws ProductNotFoundException, InsufficientStockException {

        int newQuantity = cartItem.getQuantity();
        Product product = productService.getProductById(cartItem.getProduct().getProductId());

        Optional <CartItem> foundCart = cartRepository.getCartByUserAndProduct(user,product);
        if (foundCart.isEmpty()) {
            throw new ProductNotFoundException("User ID not founded");
        }

        CartItem newItem = foundCart.get();

        if (product.getStockQuantity() >= newQuantity) {
            cartItem.setQuantity(newQuantity);
        } else throw new InsufficientStockException("Insufficient product on stock", product);

       return cartItemRepository.save(newItem);
    }

    @Transactional
    public CartTotalResponse deleteProductFromCart(Long productId, User user) throws ProductNotFoundException, UserNotFoundException {
        Product product = productService.getProductById(Long.valueOf(productId));

        Optional<CartItem> foundCart = cartRepository.getCartByUserAndProduct(user, product);
        if (foundCart.isPresent()) {
            CartRepository item = (CartRepository) foundCart.get();
            cartRepository.delete((Cart) item);
        } else throw new ProductNotFoundException("Product not found on reference id");

        return getCartTotal(String.valueOf(user));
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

    public List<CartItem> getCartByUser(User user) {
        return new ArrayList<>(cartRepository.getCartsByUser(user));
    }

    public void deleteItems(User user) {
        List<CartItem> cartItemList = cartRepository.getCartsByUser(user);
        cartRepository.deleteAll();
    }

}

 */

}
