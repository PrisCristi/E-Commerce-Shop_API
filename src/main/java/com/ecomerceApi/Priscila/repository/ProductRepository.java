package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndPrice(String name, double price);

    @Override
    Page<Product> findAll(Pageable pageable);
}

