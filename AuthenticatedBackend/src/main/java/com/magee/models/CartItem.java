package com.magee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private ApplicationUser appUser;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    @Column(name = "quantity")
    private int quantity;






}
