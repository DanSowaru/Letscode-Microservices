package com.letscode.apisales.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CartSaleRequest {
    private String id;
    private Integer userId;
    private Boolean status;
    private BigDecimal totalPrice;
    private Map<String, BigDecimal> products;
}
