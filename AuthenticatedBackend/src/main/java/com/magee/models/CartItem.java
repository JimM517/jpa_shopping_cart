package com.magee.models;

import jakarta.persistence.*;

import java.util.Objects;

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


    public CartItem() {};

    public CartItem(Long cartItemId, ApplicationUser appUser, Product product, int quantity) {
        this.cartItemId = cartItemId;
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
    }


    public Long getCartItemId() {
        return this.cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public ApplicationUser getAppUser() {
        return this.appUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", appUser=" + appUser +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Objects.equals(cartItemId, cartItem.cartItemId) && Objects.equals(appUser, cartItem.appUser) && Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemId, appUser, product, quantity);
    }
}
