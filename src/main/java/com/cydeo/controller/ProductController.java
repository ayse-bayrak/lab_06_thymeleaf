package com.cydeo.controller;

import com.cydeo.model.Product;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class ProductController {

private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/list") //localhost:8080/list
    public String listProduct(Model model){
        model.addAttribute("products",productService.listProducts());
        return "product/list";
    }
    //No Product Yet-Create Product-See Cart

    @GetMapping("/create-form")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "/product/create-product";
    } // empty new product screen

    @PostMapping("/create-form") //localhost:8080/create-form
    public String newProduct(@ModelAttribute("product") Product product, Model model){
        product.setId(UUID.randomUUID());
        productService.productCreate(product);
        return "redirect:create-form";
    }
}