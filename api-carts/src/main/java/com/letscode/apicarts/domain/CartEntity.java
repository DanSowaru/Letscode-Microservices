package com.letscode.apicarts.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.HashMap;


@Document(collection = "carts")
public class CartEntity {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String userId;

    @Field
    @Getter
    @Setter
    private HashMap<String,Integer> listProducts;

    @Getter
    @Setter
    private BigDecimal totalPrice;

    @Getter
    @Setter
    private Boolean status;

    public CartEntity(String userId) {
        this.userId = userId;
        this.status = true;
        this.listProducts = new HashMap<>();
        this.totalPrice = BigDecimal.ZERO;
    }
}
