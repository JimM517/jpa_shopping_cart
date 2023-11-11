package com.magee.repository;

import com.magee.models.ApplicationUser;
import com.magee.models.CartItem;
import com.magee.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CartItemRepositoryTest {


    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void addCartItem() {

        Optional<ApplicationUser> currentUser = userRepository.findById(1);


        Optional<Product> product = productRepository.findById(1L);

        Optional<Product> newProd = productRepository.findById(2L);

        CartItem firstCartItem = CartItem.builder()
                .appUser(currentUser.get())
                .product(product.get())
                .quantity(2)
                .build();


        CartItem secondItem = CartItem.builder()
                .appUser(currentUser.get())
                .product(newProd.get())
                .quantity(1)
                .build();

        cartItemRepository.save(firstCartItem);
        cartItemRepository.save(secondItem);
    }






}