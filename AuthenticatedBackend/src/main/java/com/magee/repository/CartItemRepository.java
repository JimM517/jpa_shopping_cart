package com.magee.repository;

import com.magee.models.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(
         "SELECT c FROM CartItem c WHERE c.cartItemId = :cartItemId AND c.appUser.userId = :userId"
    )
    CartItem getCartItemByCartItemIdAndUserId(Long cartItemId, Long userId);



    @Query(
            "SELECT c FROM CartItem c WHERE c.product.productId = :productId AND c.appUser.userId = :userId"

    )
    CartItem getCartItemByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);




    @Query(
            "SELECT c FROM CartItem c WHERE c.appUser.userId = :userId ORDER BY c.cartItemId"
    )
    List<CartItem> getCartItemByUserId(@Param("userId") Long userId);


    // Add cart item?



    @Modifying
    @Query(
            "UPDATE CartItem c SET c.quantity = :quantity WHERE c.cartItemId = :cartItemId"
    )
    void updateQuantityForCartItem(@Param("cartItemId") Long cartItemId, @Param("quantity") int quantity);


//    @Modifying
//    @Transactional
//    @Query(
//            "DELETE FROM CartItem c WHERE c.cartItemId = :cartItemId AND c.appUser.userId = :userId"
//    )
//    int deleteCartItemByCartItemIdAndUserId(@Param("cartItemId") Long cartItemId, @Param("userId") Long userId);
    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM cart_items WHERE cart_item_id = ?1 AND user_id = ?2",
            nativeQuery = true
    )
    int deleteCartItemByCartItemIdAndApplicationUserId(Long cartItemId, Long userId);


    @Modifying
    @Transactional
    @Query(
            "DELETE FROM CartItem c WHERE c.appUser.userId = :userId"
    )
    int deleteCartItemsByUserId(@Param("userId") Long userId);





}
