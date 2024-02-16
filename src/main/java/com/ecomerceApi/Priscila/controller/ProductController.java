/*
package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', ROLE_CUSTOMER)")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        Product foundProduct = productService.getProductById(productId);
        return ResponseEntity.ok().body(foundProduct);
    }
    @PostMapping ("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product) throws ProductExistsException {

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
        return ResponseEntity.ok().body(new ProductExistsException("Product successfully deleted"));
    }

    @GetMapping(params = {"page", "size"})
    public List<Product> findPaginated(@RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       HttpServletResponse response) throws ProductNotFoundException {

        Page<Product> resultPage = productService.getAllProductsOnPage(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new ProductNotFoundException("Page not found");
        }
        return resultPage.getContent();
    }
}

 */

