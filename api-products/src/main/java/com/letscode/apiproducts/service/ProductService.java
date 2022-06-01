package com.letscode.apiproducts.service;


import com.letscode.apiproducts.domain.ProductEntity;
import com.letscode.apiproducts.domain.ProductRequest;
import com.letscode.apiproducts.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity toEntity(ProductRequest productRequest) {
        ProductEntity entity = new ProductEntity(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getInventory()
        );
        return productRepository.save(entity);
    }

    public ProductEntity getById(String idProduto) {
        return productRepository.findById(idProduto).orElseThrow();
    }
}
