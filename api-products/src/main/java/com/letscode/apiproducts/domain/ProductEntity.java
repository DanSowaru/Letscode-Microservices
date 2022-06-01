package com.letscode.apiproducts.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document(collection = "produtos")
public class ProductEntity {


    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Integer inventory;
    private String description;


    public ProductEntity(String name, String description, BigDecimal price, Integer inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
    }

}
