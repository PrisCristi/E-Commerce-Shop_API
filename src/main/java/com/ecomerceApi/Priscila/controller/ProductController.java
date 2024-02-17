
package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product updateProduct(@PathVariable long id,
                                 @Valid @RequestBody Product updateProduct) throws ProductNotFoundException {

        Product foundProduct = productService.getProductById(id);
        foundProduct.setName(updateProduct.getName());
        foundProduct.setDescription(updateProduct.getDescription());
        foundProduct.setPrice(updateProduct.getPrice());
        foundProduct.setStockQuantity(updateProduct.getStockQuantity());

        return productService.updateProduct(foundProduct);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
// TODO: 17.02.24 Will implement pagination later.
    /*
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

     */


