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
    @Transactional
    @Rollback
    @Test
    public void deleteCartItem() {

//        Optional<CartItem> item = cartItemRepository.findById(1L);
//        Optional<ApplicationUser> user = userRepository.findById(1L);
//
//        assertTrue(item.isPresent());
//        assertTrue(user.isPresent());
//
//        int deletedRow = cartItemRepository.deleteCartItemByCartItemIdAndUserId(item.get().getCartItemId(), user.get().getUserId());
//
//        // Assert that the delete operation was successful
//        assertEquals(1, deletedRow);
//
//        // Try to fetch the deleted item again
//        Optional<CartItem> deletedItem = cartItemRepository.findById(item.get().getCartItemId());
//
//        // Assert that the item is not present in the database
//        assertTrue(deletedItem.isPresent());


        CartItem item = cartItemRepository.findById(1L).get();

        ApplicationUser user = userRepository.findById(1L).get();



        int deletedRow = cartItemRepository.deleteCartItemByCartItemIdAndUserId(item.getCartItemId(), user.getUserId());

        assertEquals(1, deletedRow);

        CartItem deleted = cartItemRepository.findById(item.getCartItemId()).get();

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