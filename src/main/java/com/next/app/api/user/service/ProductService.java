package com.next.app.api.user.service;


import com.next.app.api.user.entity.Product;
import com.next.app.api.user.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 상품이 존재하지 않습니다."));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProduct(id);
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setCategory(updatedProduct.getCategory());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStockQty(updatedProduct.getStockQty());
        Product saved = productRepository.save(existing);

        log.info("상품 {} 가 업데이트되었습니다.", id);

        return saved;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
