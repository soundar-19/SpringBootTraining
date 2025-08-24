package com.sr.shop_pro.service;

import com.sr.shop_pro.domain.Category;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.dto.response.ProductResponse;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.ProductRepository;
import com.sr.shop_pro.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(new BigDecimal("99.99"));
        testProduct.setCategory(Category.ELECTRONICS);
    }

    @Test
    void testFindById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        ProductResponse result = productService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(1L));
        verify(productRepository).findById(1L);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(testProduct));
        
        when(productRepository.findAll(pageable)).thenReturn(productPage);

        Page<ProductResponse> result = productService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Product", result.getContent().get(0).getName());
        verify(productRepository).findAll(pageable);
    }

    @Test
    void testSave() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        ProductResponse result = productService.save(testProduct);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository).save(testProduct);
    }

    @Test
    void testUpdate_Success() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(new BigDecimal("199.99"));
        updatedProduct.setCategory(Category.BOOKS);

        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        ProductResponse result = productService.update(1L, updatedProduct);

        assertNotNull(result);
        verify(productRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testDeleteById_Success() {
        when(productRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> productService.deleteById(1L));
        verify(productRepository).existsById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testDeleteById_NotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteById(1L));
        verify(productRepository).existsById(1L);
        verify(productRepository, never()).deleteById(1L);
    }

    @Test
    void testFindByCategory() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(testProduct));
        
        when(productRepository.findByCategory(Category.ELECTRONICS, pageable)).thenReturn(productPage);

        Page<ProductResponse> result = productService.findByCategory(Category.ELECTRONICS, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Product", result.getContent().get(0).getName());
        verify(productRepository).findByCategory(Category.ELECTRONICS, pageable);
    }

    @Test
    void testFindByName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(testProduct));
        
        when(productRepository.findByNameContainingIgnoreCase("Test", pageable)).thenReturn(productPage);

        Page<ProductResponse> result = productService.findByName("Test", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Product", result.getContent().get(0).getName());
        verify(productRepository).findByNameContainingIgnoreCase("Test", pageable);
    }

    @Test
    void testFindByPriceRange() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(testProduct));
        BigDecimal minPrice = new BigDecimal("50.00");
        BigDecimal maxPrice = new BigDecimal("150.00");
        
        when(productRepository.findByPriceBetween(minPrice, maxPrice, pageable)).thenReturn(productPage);

        Page<ProductResponse> result = productService.findByPriceRange(minPrice, maxPrice, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Product", result.getContent().get(0).getName());
        verify(productRepository).findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Test
    void testUpdate_NotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.update(1L, updatedProduct));
        verify(productRepository).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }
}