package com.letscode.apisales.handler;


import com.letscode.apisales.domain.SaleEntity;
import com.letscode.apisales.domain.SaleRequest;
import com.letscode.apisales.repository.SaleRepository;
import com.letscode.apisales.service.SaleService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SaleHandler {



    private final SaleService saleService;
    private final SaleRepository saleRepository;

    public SaleHandler(SaleService saleService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
    }


    public Mono<ServerResponse> createSale(ServerRequest serverRequest){
        return serverRequest.bodyToMono(SaleRequest.class)
                .flatMap(saleService::addSale)
                .flatMap(saleRepository::save)
                .flatMap(saleEntity -> ServerResponse
                        .created(URI.create(String.format("/sale/%s", saleEntity.getId()))).bodyValue(new SaleEntity()))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Usuario não localizado"));
    }

    public Mono<ServerResponse> getSalesPerUser(ServerRequest request) {
        return request.bodyToFlux(SaleRequest.class)
                .flatMap(saleService::getSaleByUser)
                .collectList().flatMap(vendas -> ServerResponse
                        .ok().bodyValue(vendas))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Usuario não localizado"));
    }
}
