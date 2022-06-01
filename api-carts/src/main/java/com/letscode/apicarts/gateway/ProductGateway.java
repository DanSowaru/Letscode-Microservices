package com.letscode.apicarts.gateway;


import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ProductGateway {

    private final RestTemplate restTemplate;


    public ProductGateway(@NotNull RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<BigDecimal> getPrice(String idProduto) {
        String url = String.format("http://produtosAPI:8082/v1/produto/preco/%s", idProduto);
        return restTemplate.getForEntity(url,BigDecimal.class);
    }

}
