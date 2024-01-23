package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

// this table was created to store the quantity of each product was ordered to dialog with Inventory
@Data
@Entity
@Table(name = "order_quantity")
public class OrderQuantities {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @ManyToOne // one product can be related to (appear in) various OrderQuantities
    @NonNull
    @JoinColumn(name = "product_id")
    private Product product; // product that is gonna be stored

    @Column(name = "quantity")
    @NonNull
    private Integer orderQuantity;

    @ManyToOne // one order can be related to various OrderQuantities
    @NonNull
    @JoinColumn(name = "order_id")
    private Order order;


}
