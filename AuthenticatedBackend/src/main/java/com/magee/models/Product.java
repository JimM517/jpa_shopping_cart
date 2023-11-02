package com.magee.models;

import java.math.BigDecimal;

public class Product {

    private Long productId;
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






}
