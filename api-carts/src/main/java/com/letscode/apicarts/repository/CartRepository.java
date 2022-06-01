package com.letscode.apicarts.repository;

import com.letscode.apicarts.domain.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartRepository extends MongoRepository<CartEntity, String> {
    @Query("{userId: ?0, status: ?1}")
    List<CartEntity> getActiveCart(String userId, Boolean status);
}
