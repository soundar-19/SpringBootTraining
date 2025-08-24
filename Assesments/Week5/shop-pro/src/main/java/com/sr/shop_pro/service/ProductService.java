package com.sr.shop_pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sr.shop_pro.domain.Category;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.dto.response.ProductResponse;
import java.math.BigDecimal;

public interface ProductService {
    ProductResponse findById(Long id);
    Page<ProductResponse> findAll(Pageable pageable);
    Page<ProductResponse> findByCategory(Category category, Pageable pageable);
    Page<ProductResponse> findByName(String name, Pageable pageable);
    Page<ProductResponse> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    ProductResponse save(Product product);
    ProductResponse update(Long id, Product product);
    void deleteById(Long id);
}
