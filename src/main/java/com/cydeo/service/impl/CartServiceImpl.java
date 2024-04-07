package com.cydeo.service.impl;

import com.cydeo.model.Cart;
import com.cydeo.model.CartItem;
import com.cydeo.model.Product;
import com.cydeo.service.CartService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    public static Cart CART = new Cart(BigDecimal.ZERO, new ArrayList<>());

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addToCart(UUID productId, Integer quantity) {
        Product product1= productService.findProductById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product1);
        BigDecimal cartTotalAmount = product1.getPrice().multiply(BigDecimal.valueOf(product1.getRemainingQuantity()).multiply(BigDecimal.valueOf(quantity)));
        CART.setCartTotalAmount(cartTotalAmount);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        CART.setCartItemList(cartItemList);
        //todo find product based on productId
        //todo initialise cart item using the found product
        //todo calculate cart total amount
        //todo add to cart
        return CART;
    }

    @Override
    public boolean deleteFromCart(UUID productId) {
        Product product = productService.findProductById(productId);
        return CART.getCartItemList().remove(product);
        //todo delete product object from cart using stream
    }
}
