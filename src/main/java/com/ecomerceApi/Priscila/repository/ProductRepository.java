package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    // TODO: 17.02.24 Coming back for pagination implementation later.
    /*
    @Override
    Page<Product> findAll(Pageable pageable);

     */
}

