package com.magee.repository;

import com.magee.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Test
    public void getAllProducts() {

        Product product = Product.builder()
                .productSku("YET-001")
                .name("Solor Geeks Yeti")
                .description("Keep Cool all day long!")
                .price(new BigDecimal("21.99"))
                .imageName("https://via.placeholder.com/350x250.jpg")
                .build();

        productRepository.save(product);


        List<Product> productList = productRepository.findAll();

        System.out.println("Products are:" + productList);


    }



    @Test
    public void findProductBySkuOrName() {

        List<Product> skuList = productRepository.findProductByProductSkuOrNameContaining("MUG", "");
        assertEquals(1, skuList.size());
        assertEquals("MUG-023", skuList.get(0).getProductSku());


        List<Product> nameList = productRepository.findProductByProductSkuOrNameContaining("", "Fake");
        assertEquals(0, nameList.size());


    }










}