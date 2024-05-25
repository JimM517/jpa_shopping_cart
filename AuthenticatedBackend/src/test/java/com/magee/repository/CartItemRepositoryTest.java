package com.magee.repository;

import com.magee.models.ApplicationUser;
import com.magee.models.CartItem;
import com.magee.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartItemRepositoryTest {


    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void addCartItem() {

        Optional<ApplicationUser> currentUser = userRepository.findById(1L);


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

    @Test
    public void updateQuantity() {

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(1L);

        if (optionalCartItem.isPresent()) {

            CartItem cartItem = optionalCartItem.get();

            int newQuantity = 4;
            cartItem.setQuantity(newQuantity);

            cartItemRepository.save(cartItem);

            Optional<CartItem> updatedCartItem = cartItemRepository.findById(cartItem.getCartItemId());
            assertTrue(updatedCartItem.isPresent());
            assertEquals(newQuantity, updatedCartItem.get().getQuantity());


        } else {
            fail("Cart Item with the specified Id was not found.");
        }
    }


    // TODO THIS TEST STILL WON'T PASS
    // TODO 5/21, won't pass
    @Transactional
    @Rollback
    @Test
    public void deleteCartItem() {
        // Check if the CartItem and ApplicationUser exist
        Optional<CartItem> optionalItem = cartItemRepository.findById(1L);
        assertTrue(optionalItem.isPresent(), "CartItem with id 1 should exist");

        CartItem item = optionalItem.get();

        Optional<ApplicationUser> optionalUser = userRepository.findById(1L);
        assertTrue(optionalUser.isPresent(), "User with id 1 should exist");

        ApplicationUser user = optionalUser.get();

        // Attempt to delete the CartItem
        int deletedRow = cartItemRepository.deleteCartItemByCartItemIdAndApplicationUserId(item.getCartItemId(), user.getUserId());
        System.out.println(deletedRow);
        assertEquals(1, deletedRow, "One row should be deleted");

        // Verify that the CartItem is indeed deleted
        Optional<CartItem> deletedItem = cartItemRepository.findById(item.getCartItemId());
        System.out.println(deletedItem);
        assertFalse(deletedItem.isPresent(), "CartItem should be deleted and not present");
    }



    /** THIS ONE WORKS **/
    @Transactional
    @Rollback
    @Test
    public void clearCart() {

        Optional<ApplicationUser> user = userRepository.findById(1L);

        assertTrue(user.isPresent());

        int deletedRowCount = cartItemRepository.deleteCartItemsByUserId(user.get().getUserId());

        assertEquals(2, deletedRowCount);

        List<CartItem> deletedItems = cartItemRepository.getCartItemByUserId(1L);
        assertEquals(0, deletedItems.size());



    }








}