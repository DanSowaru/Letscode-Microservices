package com.letscode.apiproducts.repository;

import com.letscode.apiproducts.domain.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
