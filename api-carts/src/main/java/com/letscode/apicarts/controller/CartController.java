package com.letscode.apicarts.controller;


import com.letscode.apicarts.domain.CartRequest;
import com.letscode.apicarts.domain.CartResponse;
import com.letscode.apicarts.domain.OrderItem;
import com.letscode.apicarts.gateway.UserGateway;
import com.letscode.apicarts.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;
    private final UserGateway userGateway;

    public CartController(CartService cartService, UserGateway userGateway) {
        this.cartService = cartService;
        this.userGateway = userGateway;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<CartResponse>> getCartById(@PathVariable String idUser) {
        return new ResponseEntity<>(cartService.getActiveCarts(idUser), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addProductOnCart(@RequestBody OrderItem orderItem) {
        ResponseEntity<String> userResponse = userGateway.getUser(orderItem.getUserId());
        if(!userResponse.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        List<CartResponse> cart = cartService.getActiveCarts(orderItem.getUserId().toString());
        if(cart.size() == 0) {
            CartRequest request = new CartRequest(orderItem.getUserId().toString());
            cartService.createCart(request);
        }
        CartResponse response = cartService.addProductOnCart(orderItem);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
