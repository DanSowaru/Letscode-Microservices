package com.letscode.apiproducts.controller;


import com.letscode.apiproducts.domain.ProductEntity;
import com.letscode.apiproducts.domain.ProductRequest;
import com.letscode.apiproducts.domain.ProductResponse;
import com.letscode.apiproducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{idProduct}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String idProduct) {


        ProductEntity entity = productService.getById(idProduct);
        ProductResponse response = new ProductResponse(entity);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }

    @PostMapping
    public ResponseEntity<ProductResponse> createNewProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(productService.toEntity(request)),HttpStatus.CREATED);
    }

    @GetMapping("/price/{idProduct}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String idProduct){
        ProductResponse response = new ProductResponse(productService.getById(idProduct));
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

}
