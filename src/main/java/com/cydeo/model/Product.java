package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer remainingQuantity;

    public Product(String name, BigDecimal price, Integer remainingQuantity) {
        this.name = name;
        this.price = price;
        this.remainingQuantity = remainingQuantity;
    }
}
