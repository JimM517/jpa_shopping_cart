package com.magee.services;

import com.magee.models.ApplicationUser;
import com.magee.models.Cart;
import com.magee.models.CartItem;
import com.magee.models.Product;
import com.magee.repository.CartItemRepository;
import com.magee.repository.ProductRepository;
import com.magee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart getUserCart(ApplicationUser user) {

        // get our user
        Long id = getUserId(user);

        // get cart items
        List<CartItem> userItems = cartItemRepository.getCartItemByUserId(id);

        // create cart
        Cart cart = new Cart(userItems);

        // get our products
        List<Product> products = productRepository.findProductByUserId(id);

        // merge product data with cart item data
        for (CartItem item : userItems) {
            item.setProduct(mergeProducts(products, item.getProduct().getProductId()));
        }

        /** CREATE TAX INFORMATION ONCE PULLED FROM API **/




        return cart;
    }

    @Override
    public void clearUserCart(ApplicationUser user) {

    }

    @Override
    public void removeFromCart(ApplicationUser user, Long itemId) {

    }

    @Override
    public CartItem addToCart(ApplicationUser user, CartItem item) {
        return null;
    }



    /** HELPER METHODS **/
    private Long getUserId(ApplicationUser user) {
        return userRepository.findByUsername(user.getUsername()).get().getUserId();
    }


    private ApplicationUser getUserByUserName(ApplicationUser user) {
        return userRepository.findByUsername(user.getUsername()).get();
    }


    private Product mergeProducts(List<Product> productList, Long productId) {

        for (Product product : productList) {

            if (product.getProductId() == productId) {
                return product;
            }
        }

        return null;

    }





}
