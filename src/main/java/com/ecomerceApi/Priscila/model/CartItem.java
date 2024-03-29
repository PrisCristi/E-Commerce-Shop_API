package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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


    //TODO: 17.02.24 check this when I work with Order.

    /*
    @Column(name = "price")
    private BigDecimal price;
    public CartItem(User user, Product product, int quantity) {
    }

    */
}


