package com.cydeo.controller;

import com.cydeo.model.Product;
import com.cydeo.service.CartService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class ProductController {

private final ProductService productService;
private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/list") //localhost:8080/list
    public String listProduct(Model model){
        model.addAttribute("productList",productService.listProducts());
        return "product/list";
    }
    //No Product Yet-Create Product-See Cart

    @GetMapping("/create-form")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "/product/create-product";
    } // empty new product screen

    @PostMapping("/create-product") //localhost:8080/create-form
    public String newProduct(@ModelAttribute("product") Product product, Model model){
        //we don't need to @ModelAttribute("product") and also we don't need to same object name (it does not have to product ==> It is able to find it based on the matching data type
        productService.productCreate(product);
        cartService.addToCart(product.getId(), product.getRemainingQuantity()); // add to cart
        return "redirect:/list";
    }

}
