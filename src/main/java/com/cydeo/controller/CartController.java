package com.cydeo.controller;

import com.cydeo.model.Cart;
import com.cydeo.model.CartItem;
import com.cydeo.model.Product;
import com.cydeo.service.CartService;
import com.cydeo.service.impl.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class CartController {

private final CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cart", CartServiceImpl.CART);
        return "/cart/show-cart";
    }

    @GetMapping("/addToCart/{productId}/{quantity}")
    public String addToCart(@PathVariable ("productId") UUID productId, @PathVariable ("quantity") Integer quantity) {

        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{productId}")
    public String deleteCartItem(@PathVariable UUID productId, Model model){
       // CartItem cartItem = CartServiceImpl.CART.getCartItemList().stream().filter(p->p.getProduct().getId().equals(productId)).findAny().orElseThrow();
        cartService.deleteFromCart(productId);
        //model.addAttribute("cart", CartServiceImpl.CART);

        return "redirect:/cart";
    }




    //@RequestParam is used to capture query parameters or form parameters from the URL,
    // while @PathVariable is used to capture values from the URL path.
    //then in your controller method, you can capture the id the regular way
    // using @PathVariable

}
