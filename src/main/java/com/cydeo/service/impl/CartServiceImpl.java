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
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    public static Cart CART = new Cart(BigDecimal.ZERO, new ArrayList<>());

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addToCart(UUID productId, Integer quantity) {
        //todo find product based on productId
        Product product= productService.findProductById(productId);
        //todo initialise cart item using the found product
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        //todo calculate cart total amount
        CART.setCartTotalAmount(CART.getCartTotalAmount().add(cartItem.getTotalAmount()));
        //todo add to cart
        CART.getCartItemList().add(cartItem);
        return CART;
    }

    @Override
    public boolean deleteFromCart(UUID productId) {
         CartItem cartItem = CART.getCartItemList()
                .stream()
                .filter(p-> p.getProduct().getId().equals(productId)).findAny().orElseThrow();
       CART.setCartTotalAmount(CART.getCartTotalAmount().subtract(cartItem.getTotalAmount()));

        return CART.getCartItemList().remove(cartItem);
        //todo delete product object from cart using stream
    }
}
