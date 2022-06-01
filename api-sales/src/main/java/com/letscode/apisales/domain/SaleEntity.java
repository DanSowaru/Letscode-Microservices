package com.letscode.apisales.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "sales")
public class SaleEntity {

    @Id
    private String id;
    private String cartId;
    private Integer userId;
    private BigDecimal finalTotalPrice;

    @Field
    private Map<String,BigDecimal> products;


    public SaleEntity(CartSaleRequest request) {
        this.userId = request.getUserId();
        this.cartId = request.getId();
        this.finalTotalPrice = request.getTotalPrice();
        this.products = request.getProducts();
    }

}
