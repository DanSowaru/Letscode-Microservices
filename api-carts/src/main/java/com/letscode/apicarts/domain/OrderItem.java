package com.letscode.apicarts.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {

    private Integer userId;
    private String productId;
    private Integer quantity;

}
