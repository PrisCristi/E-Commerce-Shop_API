package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "product_name")
    @NonNull
    private String name;

    @Column
    private String description;

    @Column
    @NonNull
    private Double price;




    /*
        @Column(name = "stock_quantity")
        private int stockQuantity;

        @Column(name = "is_stored")
        private boolean stored;

        */


}
