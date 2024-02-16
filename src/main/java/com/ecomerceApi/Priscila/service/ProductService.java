package com.ecomerceApi.Priscila.service;

import com.ecomerceApi.Priscila.exception.ProductNotFoundException;
import com.ecomerceApi.Priscila.model.Product;
import com.ecomerceApi.Priscila.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product getProductById(Long productId) throws ProductNotFoundException {

        Optional<Product> foundProduct = productRepository.findById(productId);
        if (foundProduct.isPresent()) {
            return foundProduct.get();
        } else throw new ProductNotFoundException("No product was not found.");

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product)  {
           return productRepository.save(product);
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {

        Optional<Product> existingProduct = productRepository.findById(product.getProductId());
        if (existingProduct.isPresent()) {
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException(" Product not found.");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }




/*

   public Product addProduct(Product product) throws ProductExistsException {
       Optional<Product> product

        if (repository.existsByNameAndPrice(product.getName(), product.getPrice())) { // checks if product exists
            throw new ProductExistsException(
                    String.format("Product already exists",
                            product.getName(), product.getPrice()));
        }
        return repository.save(product);
    }


    @Transactional
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

    public Page<Product> getAllProductsOnPage(int page, int size) throws ProductNotFoundException {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = repository.findAll(pageable);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    String.format("Page not found",
                            products.getPageable()));
        }
        return products;
    }

 */
}
