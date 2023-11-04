package com.magee.repository;

import com.magee.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void addProduct() {

        Product product  = Product.builder()

                .productSku("MUG-023")
                .name("Solar Geeks coffee mug")
                .description("Start your day off right!")
                .price(new BigDecimal("14.99"))
                .imageName("https://via.placeholder.com/350x250.jpg")
                .build();


        productRepository.save(product);
    }





}