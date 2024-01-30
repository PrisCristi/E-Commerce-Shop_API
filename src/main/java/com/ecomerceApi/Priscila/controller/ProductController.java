package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/id/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<Product> getProductById( @PathVariable Long productId) throws ProductNotFoundException {
        return Optional.ofNullable(productService.getProductById(productId));
    }

    @PostMapping("/add")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);

    }

}
