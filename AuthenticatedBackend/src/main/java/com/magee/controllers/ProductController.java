package com.magee.controllers;

import com.magee.models.Product;
import com.magee.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/inventory")
    public List<Product> getInventory(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
        if (sku != null) {
            return productService.getProductBySku(sku);
        } else if (name != null) {
            return productService.getProductByName(name);
        } else {
            return productService.getAllProducts();
        }
    }


    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        Product foundProduct = productService.findByProductId(productId);

        if (foundProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product was not found with the provided id, please check and try again.");
        }
        return foundProduct;
    }














}
