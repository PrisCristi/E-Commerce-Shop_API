package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "product_id")
    private List<CartProduct> cartProductList;



}

