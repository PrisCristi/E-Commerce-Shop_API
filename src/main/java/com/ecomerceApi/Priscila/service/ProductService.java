package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;
    public Product addNewProduct(Product product){
        return productRepository.save(product);

    }

}
