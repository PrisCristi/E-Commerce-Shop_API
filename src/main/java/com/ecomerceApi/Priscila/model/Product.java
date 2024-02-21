package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "product_name")
    @NonNull
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "price" )
    @NonNull
    private Double price;

    @Column
    @NonNull
    @Min(0)
    private int stockQuantity;



}
