package com.letscode.apisales.gateway;


import com.letscode.apisales.domain.CartSaleRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class CartGateway {

    public Mono<CartSaleRequest> getActiveCart(Integer usuarioId) {
        return WebClient
                .builder()
                .baseUrl(String.format("http://cartAPI:8082/v1/cart/%s", usuarioId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(CartSaleRequest.class)
                .log()
                .onErrorResume(WebClientResponseException.class, error -> error.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(error));
    }
}
