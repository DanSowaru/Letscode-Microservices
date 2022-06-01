package com.letscode.apisales.repository;

import com.letscode.apisales.domain.SaleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface VendaRepository extends ReactiveMongoRepository<SaleEntity, String> {

    Flux<SaleEntity> findAllByUsuarioId(Integer id);
}
