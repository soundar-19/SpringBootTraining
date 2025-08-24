package com.sr.shop_pro.service;

import com.sr.shop_pro.domain.*;
import com.sr.shop_pro.dto.request.OrderRequest;
import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.exception.EmptyCartException;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.*;
import com.sr.shop_pro.service.Impl.OrderServiceImpl;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private OrderItemRepository orderItemRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private AuthService authService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User testUser;
    private Order testCart;
    private Product testProduct;
    private OrderRequest createOrderRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setRole(Role.CUSTOMER);
        
        testCart = new Order();
        testCart.setId(1L);
        testCart.setUser(testUser);
        testCart.setStatus("CART");
        testCart.setOrderDate(LocalDateTime.now());
        
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(new BigDecimal("99.99"));
        testProduct.setCategory(Category.ELECTRONICS);
        
        CartItemRequest itemRequest = new CartItemRequest();
        itemRequest.setProductId(1L);
        itemRequest.setQuantity(2);
        
        createOrderRequest = new OrderRequest();
        createOrderRequest.setItems(List.of(itemRequest));
    }

    @Test
    void testGetMyCart_ExistingCart() {
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.of(testCart));

        Order result = orderService.getMyCart();

        assertNotNull(result);
        assertEquals("CART", result.getStatus());
        assertEquals(testUser, result.getUser());
        verify(orderRepository).findByUserIdAndStatus(1L, "CART");
    }

    @Test
    void testGetMyCart_CreateNewCart() {
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenReturn(testCart);

        Order result = orderService.getMyCart();

        assertNotNull(result);
        verify(orderRepository).findByUserIdAndStatus(1L, "CART");
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_Success() {
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.of(testCart));
        when(orderItemRepository.countByOrderId(1L)).thenReturn(2L);
        when(orderRepository.save(any(Order.class))).thenReturn(testCart);

        Order result = orderService.placeOrder();

        assertNotNull(result);
        verify(orderItemRepository).countByOrderId(1L);
        verify(orderRepository, times(2)).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_EmptyCart() {
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.of(testCart));
        when(orderItemRepository.countByOrderId(1L)).thenReturn(0L);

        assertThrows(EmptyCartException.class, () -> orderService.placeOrder());
        verify(orderItemRepository).countByOrderId(1L);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testFindById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testCart));
        when(authService.isAdmin()).thenReturn(false);
        when(authService.getCurrentUser()).thenReturn(testUser);

        Order result = orderService.findById(1L);

        assertNotNull(result);
        assertEquals(testCart, result);
        verify(orderRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.findById(1L));
        verify(orderRepository).findById(1L);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(Arrays.asList(testCart));
        
        when(orderRepository.findAll(pageable)).thenReturn(orderPage);

        Page<Order> result = orderService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(orderRepository).findAll(pageable);
    }

    @Test
    void testGetMyOrders() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(Arrays.asList(testCart));
        
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserId(1L, pageable)).thenReturn(orderPage);

        Page<Order> result = orderService.getMyOrders(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(orderRepository).findByUserId(1L, pageable);
    }

    @Test
    void testGetMyOrdersByDateRange() {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();
        Page<Order> orderPage = new PageImpl<>(Arrays.asList(testCart));
        
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndOrderDateBetween(1L, start, end, pageable)).thenReturn(orderPage);

        Page<Order> result = orderService.getMyOrdersByDateRange(start, end, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(orderRepository).findByUserIdAndOrderDateBetween(1L, start, end, pageable);
    }

    @Test
    void testCreateCompleteOrder_Success() {
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.save(any(Order.class))).thenReturn(testCart);
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(new OrderItem());

        Order result = orderService.createCompleteOrder(createOrderRequest);

        assertNotNull(result);
        verify(orderRepository).save(any(Order.class));
        verify(productRepository).findById(1L);
        verify(orderItemRepository).save(any(OrderItem.class));
    }

    @Test
    void testCreateCompleteOrder_EmptyItems() {
        OrderRequest emptyRequest = new OrderRequest();
        emptyRequest.setItems(List.of());

        assertThrows(EmptyCartException.class, () -> orderService.createCompleteOrder(emptyRequest));
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testUpdateStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testCart));
        when(authService.isAdmin()).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenReturn(testCart);

        Order result = orderService.updateStatus(1L, "SHIPPED");

        assertNotNull(result);
        verify(orderRepository).findById(1L);
        verify(orderRepository).save(testCart);
    }

    @Test
    void testGetMyCartItems() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<OrderItem> itemPage = new PageImpl<>(Arrays.asList(new OrderItem()));
        
        when(authService.getCurrentUser()).thenReturn(testUser);
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.of(testCart));
        when(orderItemRepository.findByOrderId(1L, pageable)).thenReturn(itemPage);

        Page<OrderItem> result = orderService.getMyCartItems(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(orderItemRepository).findByOrderId(1L, pageable);
    }
}