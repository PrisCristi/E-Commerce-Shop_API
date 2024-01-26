package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;


    @PostMapping("/add") // /product/add       - add new product
    @PreAuthorize("hasRole('ADMIN')")   // define authorization, who can add products.
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductExistsException {

        Product addedNewProduct = productService.addedNewProduct(product);
        return ResponseEntity.ok().body(addedNewProduct);
    }

    @PutMapping("/{id}") // update an existent product
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updatedProduct(@PathVariable("id") long id,
                                                  @RequestBody Product product) throws ProductNotFoundException {

        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @GetMapping("/{id}") // get  products by id
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')") // to see the products anybody
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotFoundException {

        Product foundProduct = productService.getById(id);
        return ResponseEntity.ok().body(foundProduct);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // only admin can delete data
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {

        productService.deleteProduct(id);
        String message = String.format("Product with id %d was deleted", id);
        return ResponseEntity.ok().body(message);
    }

}
