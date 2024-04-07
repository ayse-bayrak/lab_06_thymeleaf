package com.cydeo.controller;

import com.cydeo.model.Product;
import com.cydeo.service.CartService;
import com.cydeo.service.impl.CartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class CartController {
private final CartService cartService;
private final ProductController productController;

    public CartController(CartService cartService, ProductController productController) {
        this.cartService = cartService;
        this.productController = productController;
    }

    @RequestMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cart", CartServiceImpl.CART);
        return "/cart/show-cart";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("id") UUID productId ){
        cartService.deleteFromCart(productId);// it does not work properly
        return "/cart/show-cart";
    }

}
