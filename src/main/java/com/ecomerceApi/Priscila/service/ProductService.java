package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    public Product addedNewProduct(Product product) throws ProductExistsException { // creates new product

        if (repository.existsByNameAndPrice(product.getName(), product.getPrice())) { // checks if product exists
            throw new ProductExistsException(
                    format("Product with name %s and price %f already exists",
                            product.getName(), product.getPrice()));
        }
        return repository.save(product);
    }

    public Product getById(long id) throws ProductNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id %d is not found", id)));
    }

    @Transactional // This annotation ensures data integrity and consistency - rolling back (rollback when is necessáry)
    public Product updateProduct(long id, Product product) throws ProductNotFoundException {

        Product productToBeUpdated = getById(id);
        product.setProductId(productToBeUpdated.getProductId());
        return repository.save(product);
    }
/*
    public boolean checkProductStock(long productId, int requestedAmount) {
        Product requestedProduct = getById(productId);
        return requestedProduct.getStock() >= requestedAmount;
    }

 */

    @Transactional
    public void deleteProduct(long id) throws ProductNotFoundException {
        repository.delete(getById(id));
    }


}
