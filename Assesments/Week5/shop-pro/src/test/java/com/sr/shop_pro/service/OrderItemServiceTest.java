package com.sr.shop_pro.service;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.OrderItem;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.domain.Role;
import com.sr.shop_pro.domain.Category;
import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.OrderItemRepository;
import com.sr.shop_pro.repository.ProductRepository;
import com.sr.shop_pro.service.Impl.OrderItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;
    
    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private OrderService orderService;
    
    @Mock
    private AuthService authService;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private User testUser;
    private Product testProduct;
    private Order testCart;
    private OrderItem testOrderItem;
    private CartItemRequest testRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setRole(Role.CUSTOMER);
        
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(new BigDecimal("99.99"));
        testProduct.setCategory(Category.ELECTRONICS);
        
        testCart = new Order();
        testCart.setId(1L);
        testCart.setUser(testUser);
        testCart.setStatus("CART");
        testCart.setOrderDate(LocalDateTime.now());
        
        testOrderItem = new OrderItem();
        testOrderItem.setId(1L);
        testOrderItem.setOrder(testCart);
        testOrderItem.setProduct(testProduct);
        testOrderItem.setQuantity(2);
        
        testRequest = new CartItemRequest();
        testRequest.setProductId(1L);
        testRequest.setQuantity(2);
    }

    @Test
    void testCreate_NewItem() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.empty());
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(testOrderItem);

        OrderItem result = orderItemService.create(testRequest);

        assertNotNull(result);
        assertEquals(2, result.getQuantity());
        verify(productRepository).findById(1L);
        verify(orderService).getMyCart();
        verify(orderItemRepository).save(any(OrderItem.class));
    }

    @Test
    void testCreate_UpdateExistingItem() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.of(testOrderItem));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(testOrderItem);

        OrderItem result = orderItemService.create(testRequest);

        assertNotNull(result);
        verify(orderItemRepository).save(testOrderItem);
    }

    @Test
    void testCreate_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderItemService.create(testRequest));
        verify(productRepository).findById(1L);
        verify(orderItemRepository, never()).save(any(OrderItem.class));
    }

    @Test
    void testUpdateCartItemByProductId_Success() {
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.of(testOrderItem));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(testOrderItem);

        OrderItem result = orderItemService.updateCartItemByProductId(1L, 5);

        assertNotNull(result);
        verify(orderService).getMyCart();
        verify(orderItemRepository).findByOrderIdAndProductId(1L, 1L);
        verify(orderItemRepository).save(testOrderItem);
    }

    @Test
    void testUpdateCartItemByProductId_NotFound() {
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, 
            () -> orderItemService.updateCartItemByProductId(1L, 5));
        verify(orderService).getMyCart();
        verify(orderItemRepository, never()).save(any(OrderItem.class));
    }

    @Test
    void testRemoveFromCartByProductId_Success() {
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.of(testOrderItem));

        assertDoesNotThrow(() -> orderItemService.removeFromCartByProductId(1L));
        verify(orderService).getMyCart();
        verify(orderItemRepository).delete(testOrderItem);
    }

    @Test
    void testRemoveFromCartByProductId_NotFound() {
        when(orderService.getMyCart()).thenReturn(testCart);
        when(orderItemRepository.findByOrderIdAndProductId(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, 
            () -> orderItemService.removeFromCartByProductId(1L));
        verify(orderService).getMyCart();
        verify(orderItemRepository, never()).delete(any(OrderItem.class));
    }

    @Test
    void testClearCart() {
        when(orderService.getMyCart()).thenReturn(testCart);

        assertDoesNotThrow(() -> orderItemService.clearCart());
        verify(orderService).getMyCart();
        verify(orderItemRepository).deleteByOrderId(1L);
    }
}