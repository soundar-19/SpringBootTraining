package com.sr.shop_pro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sr.shop_pro.domain.Category;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.dto.response.ProductResponse;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.ProductRepository;
import com.sr.shop_pro.service.ProductService;
import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductResponse.toDTO(product);
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponse::toDTO);
    }

    @Override
    public Page<ProductResponse> findByCategory(Category category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable).map(ProductResponse::toDTO);
    }

    @Override
    public Page<ProductResponse> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable).map(ProductResponse::toDTO);
    }

    @Override
    public Page<ProductResponse> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable).map(ProductResponse::toDTO);
    }

    @Override
    public ProductResponse save(Product product) {
        Product savedProduct = productRepository.save(product);
        return ProductResponse.toDTO(savedProduct);
    }

    @Override
    public ProductResponse update(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductResponse.toDTO(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
