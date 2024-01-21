package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { // jpa will manage also this repo



}
