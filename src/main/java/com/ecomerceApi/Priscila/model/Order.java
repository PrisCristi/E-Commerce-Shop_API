package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;   // each order must have a unique id number

    @ManyToOne // one customer can have multiple orders. Need User in this column.
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order") // each list is associated to one order.
    private List<ShoppingCart> quantities = new ArrayList<>(); // created a new table to store the quantity of products per order


}
