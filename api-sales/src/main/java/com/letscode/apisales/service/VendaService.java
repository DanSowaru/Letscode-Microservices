package com.letscode.apisales.service;


import com.letscode.apisales.domain.SaleEntity;
import com.letscode.apisales.domain.SaleRequest;
import com.letscode.apisales.gateway.CartGateway;
import com.letscode.apisales.gateway.UsuarioGateway;
import com.letscode.apisales.repository.VendaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendaService {
    private final UsuarioGateway usuarioGateway;
    private final CartGateway cartGateway;
    private final VendaRepository vendaRepository;

    public VendaService(UsuarioGateway usuarioGateway, CartGateway cartGateway, VendaRepository vendaRepository) {
        this.usuarioGateway = usuarioGateway;
        this.cartGateway = cartGateway;
        this.vendaRepository = vendaRepository;
    }

    public Mono<SaleEntity> adicionaVenda(SaleRequest request){
        return Mono.zip(
                Mono.just(request).flatMap(venda -> usuarioGateway.getUsuario(venda.getUserId())),
                Mono.just(request).flatMap(venda -> cartGateway.getActiveCart(venda.getUserId()))
        ).map(tupla -> new SaleEntity(tupla.getT2()));
    }

    public Flux<SaleEntity> getVendaPorUsuario(SaleRequest saleRequest){
        return Flux.just(saleRequest.getUserId()).flatMap(id -> vendaRepository.findAllByUsuarioId(id));
    }
}
