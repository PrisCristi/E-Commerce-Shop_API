package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.exception.ProductExistsException;
import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;

    public Product addProduct(Product product) throws ProductExistsException { // creates new product

        if (repository.existsByNameAndPrice(product.getName(), product.getPrice())) { // checks if product exists
            throw new ProductExistsException(
                    String.format("Product already exists",
                            product.getName(), product.getPrice()));
        }
        return repository.save(product);
    }

    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = repository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else throw new ProductNotFoundException("No product was not found.");
    }

    @Transactional // This annotation ensures data integrity and consistency - rolling back (rollback when is necessáry)
    public Product updateProduct(long id, Product product) throws ProductNotFoundException {

        Product productToBeUpdated = getProductById(id);
        product.setProductId(productToBeUpdated.getProductId());
        return repository.save(product);
    }

    public boolean checkProductStock(long productId, int requestedTotal) throws ProductNotFoundException {
        Product requestedProduct = getProductById(productId);
        return requestedProduct.getStockQuantity() >= requestedTotal;
    }

    @Transactional
    public boolean deleteProduct(long id) throws ProductNotFoundException {
        repository.delete(getProductById(id));
        return true;
    }

    public Page<Product> getAllProducts(int page, int size) throws ProductNotFoundException {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAll(pageable);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    String.format("Page not found",
                            products.getPageable()));
        }
        return products;
    }
}
