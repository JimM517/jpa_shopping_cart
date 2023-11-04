package com.magee.services;

import com.magee.models.Product;
import com.magee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product findByProductId(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // TODO Still need to implement this
    @Override
    public List<Product> getProductByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Product> getProductBySku(String productSku) {
        return productRepository.findProductByProductSkuContaining(productSku);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findProductByNameContaining(name);
    }

    // TODO need to double check this implementation
    @Override
    public Product update(Product updatedProduct) {
        return productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }





}
