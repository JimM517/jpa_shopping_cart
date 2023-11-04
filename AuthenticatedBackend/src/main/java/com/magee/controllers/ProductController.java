package com.magee.controllers;

import com.magee.models.Product;
import com.magee.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/inventory")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }




}
