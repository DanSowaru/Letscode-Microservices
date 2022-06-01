package com.letscode.apisales.handler;


import com.letscode.apisales.domain.SaleEntity;
import com.letscode.apisales.domain.SaleRequest;
import com.letscode.apisales.repository.VendaRepository;
import com.letscode.apisales.service.VendaService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class VendaHandler {



    private final VendaService vendaService;
    private final VendaRepository vendaRepository;

    public VendaHandler(VendaService vendaService, VendaRepository vendaRepository) {
        this.vendaService = vendaService;
        this.vendaRepository = vendaRepository;
    }


    public Mono<ServerResponse> criaVenda(ServerRequest serverRequest){
        return serverRequest.bodyToMono(SaleRequest.class)
                .flatMap(vendaService::adicionaVenda)
                .flatMap(vendaRepository::save)
                .flatMap(saleEntity -> ServerResponse
                        .created(URI.create(String.format("/venda/%s", saleEntity.getId()))).bodyValue(new SaleEntity()))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Usuario não existente"));
    }

    public Mono<ServerResponse> getVendaPorUsuario(ServerRequest request) {
        return request.bodyToFlux(SaleRequest.class)
                .flatMap(vendaService::getVendaPorUsuario)
                .collectList().flatMap(vendas -> ServerResponse
                        .ok().bodyValue(vendas))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Usuario não existente"));
    }
}
