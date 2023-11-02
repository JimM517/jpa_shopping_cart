package com.magee.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_sku")
    private String productSku;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageName;


    public Product() {};


    public Product(Long productId, String productSku, String name, String description, BigDecimal price, String imageName) {
        this.productId = productId;
        this.productSku = productSku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }


    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSku() {
        return this.productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productSku='" + productSku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productSku, product.productSku) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(imageName, product.imageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productSku, name, description, price, imageName);
    }


}
