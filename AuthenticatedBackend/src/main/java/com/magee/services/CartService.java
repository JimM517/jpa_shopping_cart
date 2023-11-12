package com.magee.services;

import com.magee.models.ApplicationUser;
import com.magee.models.Cart;
import com.magee.models.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartService {


    // TODO GOING TO TRY AND TEST THESE METHODS


   Cart getUserCart(ApplicationUser user);

   void clearUserCart(ApplicationUser user);

   void removeFromCart(ApplicationUser user, Long itemId);

   CartItem addToCart(ApplicationUser user, CartItem item);









}
