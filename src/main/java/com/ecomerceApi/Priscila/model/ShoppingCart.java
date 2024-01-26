package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

// this table was created to store the quantity of each product
@Getter
@Setter
@Entity
@Table(name = "cart")
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "product_id")
    private Product product; // product that is gonna be stored

    @Column(name = "quantity")
    @NonNull
    private int quantity;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private User user;


}
