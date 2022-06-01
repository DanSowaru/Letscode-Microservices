package com.letscode.apisales.service;


import com.letscode.apisales.domain.SaleEntity;
import com.letscode.apisales.domain.SaleRequest;
import com.letscode.apisales.gateway.CartGateway;
import com.letscode.apisales.gateway.UserGateway;
import com.letscode.apisales.repository.SaleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SaleService {
    private final UserGateway userGateway;
    private final CartGateway cartGateway;
    private final SaleRepository saleRepository;

    public SaleService(UserGateway userGateway, CartGateway cartGateway, SaleRepository saleRepository) {
        this.userGateway = userGateway;
        this.cartGateway = cartGateway;
        this.saleRepository = saleRepository;
    }

    public Mono<SaleEntity> addSale(SaleRequest request){
        return Mono.zip(
                Mono.just(request).flatMap(sale -> userGateway.getUser(sale.getUserId())),
                Mono.just(request).flatMap(sale -> cartGateway.getActiveCart(sale.getUserId()))
        ).map(tuple -> new SaleEntity(tuple.getT2()));
    }

    public Flux<SaleEntity> getSaleByUser(SaleRequest saleRequest){
        return Flux.just(saleRequest.getUserId()).flatMap(id -> saleRepository.findAllByUserId(id));
    }
}
