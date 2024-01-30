package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', CUSTOMER)")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        Product foundProduct = productService.getProductById(productId);
        return ResponseEntity.ok().body(foundProduct);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(
            @Valid @RequestBody Product product) throws ProductExistsException {

        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.ok().body(addedProduct);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id,
                                                 @Valid @RequestBody Product product) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductExistsException> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(new ProductExistsException("Product sucessfuly deleted"));
    }
}
