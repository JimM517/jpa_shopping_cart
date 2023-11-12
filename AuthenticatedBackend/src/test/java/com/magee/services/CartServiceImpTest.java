package com.magee.services;

import com.magee.repository.CartItemRepository;
import com.magee.repository.ProductRepository;
import com.magee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CartServiceImpTest {

    // TODO CREATE TESTS

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CartItemRepository cartItemRepository;




}