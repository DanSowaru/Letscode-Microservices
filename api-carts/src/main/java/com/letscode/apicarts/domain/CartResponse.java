package com.letscode.apicarts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@AllArgsConstructor
public class CartResponse {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private Boolean status;

    @Getter
    @Setter
    private HashMap<String, Integer> productList;

    public CartResponse(CartEntity cartEntity) {
        this.userId = cartEntity.getUserId();
        this.status = cartEntity.getStatus();
        this.productList = cartEntity.getListProducts();
    }


}
