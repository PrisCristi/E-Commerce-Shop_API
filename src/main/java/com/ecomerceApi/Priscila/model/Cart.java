package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne   // ONE cart associated to ONE user.
    @NonNull
    @JoinColumn(name = "user_id")
    private User user;




//    @ManyToOne
//    @NonNull
//    @JoinColumn(name = "product_id")
//    private Product product;

//    public Cart(long id) {
//        this.id = id;
//    }
}

