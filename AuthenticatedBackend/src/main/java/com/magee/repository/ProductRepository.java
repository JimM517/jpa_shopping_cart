package com.magee.repository;

import com.magee.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // what methods do we need?
    // getAll(), getById(), getByUserId(), getByWishListId(), findByProductSkuAndName()


    //TODO 11/11 STILL NEED TO IMPLEMENT THIS
    @Query(
            value = "SELECT * FROM product WHERE product_id IN (SELECT product_id IN cart_item WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Product> findProductByUserId(Long userId);


    List<Product> findProductByProductSkuOrNameContaining(String productSku, String name);


    List<Product> findProductByProductSkuContaining(String productSku);

    List<Product> findProductByNameContaining(String name);


    @Modifying
    @Transactional
    @Query(
            value = "UPDATE Product p SET p.productSku = :productSku, p.name = :name, p.description = :description, p.price = :price, p.imageName = :imageName WHERE p.productId = :productId "
    )
    void updateProduct(@Param("productId") Long productId, @Param("productSku") String productSku, @Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price, @Param("imageName") String imageName);



}
