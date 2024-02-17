
package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.CartNotFoundException;
import com.ecomerceApi.Priscila.model.Cart;
import com.ecomerceApi.Priscila.service.CartService;
import com.ecomerceApi.Priscila.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    // TODO: 17.02.24 Create create cart, addToCart, updateItemInTheCart, deleteItemInThe Cart methods

    private final CartService cartService;
    private final UserDetailsServiceImpl userDetailsService;

    /*
    @PostMapping("/add")
    public Cart addCart(@RequestBody @Valid CartRepository repository) {
        User existingUser = cartService.getCartBy;
        Cart newCart = new Cart();
        newCart.setUser(existingUser);
        Cart cartFromDb = cartService.createCart(newCart);
        return new CartDto(cartFromDb.getId(), cartDto.getUserId());
    }
*/

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) throws CartNotFoundException, CartNotFoundException {
        return cartService.getCartById(id);
    }


    /*
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails principal) {

        try {
            return ResponseEntity.ok(cartService.getCartTotal(String.valueOf(
                    userService.getUserByEmail(principal.getUsername()))));

        } catch (UserNotFoundException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addToCart(@AuthenticationPrincipal UserDetails principal,
                                       @RequestBody CartItem cartItem) {
        try {
            User user = userService.getUserByEmail(principal.getUsername());
            return ResponseEntity.ok(cartService.addProductToCart(cartItem, user));
        } catch (InsufficientStockException | ProductNotFoundException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateProduct(@AuthenticationPrincipal UserDetails principal,
                                           @RequestBody CartItem cartItem) {

        try {
            User user = userService.getUserByEmail(principal.getUsername());
            return ResponseEntity.ok(cartService.updateProduct(cartItem, user));
        } catch (ProductNotFoundException |
                 InsufficientStockException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{product-id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteFromCart(@AuthenticationPrincipal UserDetails principal,
                                            @RequestBody CartItem cartItem) {
        try {
            User user = userService.getUserByEmail(principal.getUsername());
            return ResponseEntity.ok(cartService.deleteProductFromCart(cartItem.getProduct().getProductId(), user));

        } catch (ProductNotFoundException | UserNotFoundException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);

        }
    }


/*
    @PostMapping("/mycart/add")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<CartResponse> addProductToMyCart(@RequestParam("productId") long productId,
                                                           @RequestParam("quantity") int quantity) throws ProductNotFoundException {
        // TODO: cartService.addProductToCart(productId, quantity);
       //TODO: return ResponseEntity.ok().body(new CartResponse("Added product to your cart"));
    return null;

    }
}
 */
    
}
