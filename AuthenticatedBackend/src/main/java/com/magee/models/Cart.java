package com.magee.models;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private BigDecimal tax;
    private List<CartItem> cartItems;

    public Cart() {
        this.tax = new BigDecimal("0.00");
        this.cartItems = new ArrayList<>();
    }


    public Cart(List<CartItem> cartItems) {
        this();
        this.cartItems = cartItems;
    }


    public CartItem[] getItems() {
        CartItem[] result = new CartItem[cartItems.size()];
        return cartItems.toArray(result);
    }


    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }



    /** TWO DERIVED PROPERTIES **/

    public BigDecimal getItemSubtotal() {
        BigDecimal subtotal = new BigDecimal("0.00");

        subtotal.setScale(2);

        for (CartItem item : cartItems) {
            subtotal = subtotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));

        }
        return subtotal;

    }


    public BigDecimal getTotal() {
        return getItemSubtotal().add(tax);
    }



}
