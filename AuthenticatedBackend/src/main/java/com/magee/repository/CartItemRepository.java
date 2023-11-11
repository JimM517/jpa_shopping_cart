package com.magee.repository;

import com.magee.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(
           value = "SELECT * FROM cart_item WHERE cart_item_id = ?1 AND user_id = ?2",
           nativeQuery = true
    )
    CartItem getCartItemByCartItemIdAndUserId(Long cartItemId, Long userId);


    @Query(
            value = "SELECT * FROM cart_item WHERE product_id = ?1 AND user_id = ?2",
            nativeQuery = true
    )
    CartItem getCartItemByProductIdAndUserId(Long productId, Long userId);






}
