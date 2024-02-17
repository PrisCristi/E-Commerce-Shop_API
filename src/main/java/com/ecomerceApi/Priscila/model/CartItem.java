package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name = "cart_Products")
public class CartItem extends Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    public CartItem(Long id, @NonNull User user, Long id1, Long cartId, Long productId, int quantity) {
        super(id, user);
        this.id = id1;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(Long id, Long cartId, Long productId, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //TODO: 17.02.24 check this when I work with Order.
    /*
    @Column(name = "price")
    private BigDecimal price;
    public CartItem(User user, Product product, int quantity) {
    }

    */
}


