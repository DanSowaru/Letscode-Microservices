package com.letscode.apicarts.service;


import com.letscode.apicarts.domain.CartEntity;
import com.letscode.apicarts.domain.CartRequest;
import com.letscode.apicarts.domain.CartResponse;
import com.letscode.apicarts.domain.OrderItem;
import com.letscode.apicarts.gateway.ProductGateway;
import com.letscode.apicarts.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductGateway productGateway;

    public CartService(CartRepository cartRepository, ProductGateway productGateway) {
        this.cartRepository = cartRepository;
        this.productGateway = productGateway;
    }

    public CartEntity toEntity(CartRequest request){
        return new CartEntity(request.getIdUser());
    }


    public List<CartResponse> getActiveCarts(String userId) {
        List<CartEntity> carts = cartRepository.getActiveCart(userId,true);
        return carts.stream().map(CartResponse::new).toList();
    }

    public ResponseEntity<CartResponse> createCart(CartRequest request) {

        List<CartEntity> carts = cartRepository.getActiveCart(request.getIdUser(),true);
        if(!carts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        CartEntity entity = toEntity(request);
        cartRepository.save(entity);
        CartResponse response = new CartResponse(entity);
        return ResponseEntity.ok(response);
    }


    public CartResponse addProductOnCart(OrderItem orderItem){
        CartEntity entity = cartRepository.getActiveCart(orderItem.getUserId().toString(),true).get(0);
        if(entity.getListProducts().size() == 0 ) {
            entity.getListProducts().put(orderItem.getProductId(), orderItem.getQuantity());
            CartResponse cartResponse = new CartResponse(cartRepository.save(entity));
            return cartResponse;
        }

        entity.getListProducts().merge(orderItem.getProductId(), orderItem.getQuantity(),(quantity, newQuantity) -> (quantity + newQuantity));
        BigDecimal totalPrice = entity.getListProducts().entrySet().stream().map(
                product -> (productGateway.getPrice(product.getKey()).getBody().multiply(BigDecimal.valueOf(product.getValue())))
        ).reduce(BigDecimal.ZERO,BigDecimal::add);
        entity.setTotalPrice(totalPrice);

        CartEntity newCartEntity = cartRepository.save(entity);
        return new CartResponse(newCartEntity);
    }
}
