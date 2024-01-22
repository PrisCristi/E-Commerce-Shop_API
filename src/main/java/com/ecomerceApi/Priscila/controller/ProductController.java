package com.ecomerceApi.Priscila.controller;

import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import com.ecomerceApi.Priscila.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("product")
public class ProductController {
    ProductService productService;

    @PostMapping("/add") // /product/add
    public Product addProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);

    }

}
