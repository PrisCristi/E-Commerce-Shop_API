
package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@NoArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {


    private ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



/*
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', ROLE_CUSTOMER)")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        Product foundProduct = productService.getProductById(productId);
        return ResponseEntity.ok().body(foundProduct);
    }

 */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product addProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Product> getProductById(@PathVariable Long id){
        return productRepository.findById(id);
    }


/*
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
}

