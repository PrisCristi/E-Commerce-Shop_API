package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NonNull
    private Long id;

    @NonNull
    @OneToOne (optional = false, orphanRemoval = true)   // orphanRemoval will exclude from DB/ one inventory to one product_id  - (Should I create the join in id?)
    @JoinColumn(name = "product_id", nullable = false)
    private Product productName;

    @Column(name = "quantity")
    @NonNull
    private Integer quantityOnStock;

}
