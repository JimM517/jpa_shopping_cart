package com.magee.repository;

import com.magee.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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



//    @Test
//    public void findProductBySkuOrName() {
//
//        List<Product> skuList = productRepository.findProductByProductSkuOrNameContaining("MUG", "");
//        assertEquals(1, skuList.size());
//        assertEquals("MUG-023", skuList.get(0).getProductSku());
//
//
//        List<Product> nameList = productRepository.findProductByProductSkuOrNameContaining("", "Fake");
//        assertEquals(0, nameList.size());
//
//
//    }


    @Test
    public void findProductBySku() {

        List<Product> skuList = productRepository.findProductByProductSkuContaining("MUG");
        assertEquals(1, skuList.size());
        assertEquals("MUG-023", skuList.get(0).getProductSku());

    }



    @Test
    public void findProductByName() {


        List<Product> nameList = productRepository.findProductByNameContaining("Yeti");
        assertEquals(1, nameList.size());
        assertEquals("Solor Geeks Yeti", nameList.get(0).getName());


    }



    @Test
    public void deleteProduct() {

        Product product = Product.builder()
                .productSku("ART-256")
                .name("Galactic poster")
                .description("Beautiful view of the galaxy.")
                .price(new BigDecimal("9.59"))
                .imageName("https://via.placeholder.com/350x250.jpg")
                .build();
        productRepository.save(product);


        Long productId = product.getProductId();


        productRepository.deleteById(productId);


        Optional<Product> deletedProduct = productRepository.findById(productId);
        assertFalse(deletedProduct.isPresent());


    }




    // TODO add tests for getting user's cart / wishlist items


















}