package com.magee.services;

import com.magee.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


        Product findByProductId(Long productId);

        List<Product> getAllProducts();

        List<Product> getProductByUserId(Long userId);

        List<Product> getProductBySku(String productSku);

        List<Product> getProductByName(String name);

        Product update(Product updatedProduct);

        void deleteProduct(Long productId);






}
