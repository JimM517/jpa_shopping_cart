package com.magee.repository;

import com.magee.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // what methods do we need?
    // getAll(), getById(), getByUserId(), getByWishListId(), findByProductSkuAndName()


    @Query(
            value = "SELECT * FROM product WHERE product_id IN (SELECT product_id IN cart_item WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Product> findProductByUserId(Long userId);


    List<Product> findProductByProductSkuOrNameContaining(String productSku, String name);



}
