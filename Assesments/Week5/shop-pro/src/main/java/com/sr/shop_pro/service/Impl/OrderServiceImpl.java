package com.sr.shop_pro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.OrderItem;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.dto.request.OrderRequest;
import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.exception.EmptyCartException;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.OrderItemRepository;
import com.sr.shop_pro.repository.OrderRepository;
import com.sr.shop_pro.repository.ProductRepository;
import com.sr.shop_pro.service.AuthService;
import com.sr.shop_pro.service.OrderService;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    



    @Override
    public Order findById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        
        // Security check: ensure user can only access their own orders (unless admin)
        if (!authService.isAdmin() && !order.getUser().getId().equals(authService.getCurrentUser().getId())) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        
        return order;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> getMyOrders(Pageable pageable) {
        User currentUser = authService.getCurrentUser();
        return orderRepository.findByUserId(currentUser.getId(), pageable);
    }

    @Override
    public Page<Order> getMyOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        User currentUser = authService.getCurrentUser();
        return orderRepository.findByUserIdAndOrderDateBetween(currentUser.getId(), startDate, endDate, pageable);
    }

    

    @Override
    public Order updateStatus(Long id, String status) {
        Order order = findById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order getMyCart() {
        User currentUser = authService.getCurrentUser();
        return orderRepository.findByUserIdAndStatus(currentUser.getId(), "CART")
            .orElseGet(() -> {
                Order newCart = new Order();
                newCart.setUser(currentUser);
                newCart.setStatus("CART");
                newCart.setOrderDate(LocalDateTime.now());
                return orderRepository.save(newCart);
            });
    }

    @Override
    public Order placeOrder() {
        User currentUser = authService.getCurrentUser();
        Order cart = orderRepository.findByUserIdAndStatus(currentUser.getId(), "CART")
            .orElseThrow(() -> new ResourceNotFoundException("No active cart found for user"));
        
        // Check if cart has items
        long itemCount = orderItemRepository.countByOrderId(cart.getId());
        if (itemCount == 0) {
            throw new EmptyCartException("Cannot place order: Cart is empty");
        }
        
        cart.setStatus("PLACED");
        cart.setOrderDate(LocalDateTime.now());
        
        Order placedOrder = orderRepository.save(cart);
        
        // Create new empty cart for user
        Order newCart = new Order();
        newCart.setUser(currentUser);
        newCart.setStatus("CART");
        newCart.setOrderDate(LocalDateTime.now());
        orderRepository.save(newCart);
        
        return placedOrder;
    }

    @Override
    @Transactional
    public Order createCompleteOrder(OrderRequest orderRequest) {
        User currentUser = authService.getCurrentUser();
        
        // Validate order has items
        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new EmptyCartException("Cannot create order: No items provided");
        }
        
        Order order = new Order();
        order.setUser(currentUser);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        
        Order savedOrder = orderRepository.save(order);
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemRequest itemRequest : orderRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemRequest.getProductId()));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            
            orderItems.add(orderItemRepository.save(orderItem));
        }
        
        savedOrder.setOrderItems(orderItems);
        return savedOrder;
    }

    @Override
    public Page<OrderItem> getMyCartItems(Pageable pageable) {
        Order cart = getMyCart();
        return orderItemRepository.findByOrderId(cart.getId(), pageable);
    }
}
