package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
        private String productName;

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
