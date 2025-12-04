package com.primeTradeAssignment.PrimeTradeAssignment.secondaryentity;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public Product createProduct(Product product) {
        return productRepo.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }


    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }


    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepo.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            return productRepo.save(product);
        });
    }


    public boolean deleteProduct(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
