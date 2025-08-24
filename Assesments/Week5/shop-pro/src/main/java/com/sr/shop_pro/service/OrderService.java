package com.sr.shop_pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.OrderItem;
import com.sr.shop_pro.dto.request.OrderRequest;
import java.time.LocalDateTime;

public interface OrderService {
    Order findById(Long id);
    Page<Order> findAll(Pageable pageable);
    Page<Order> getMyOrders(Pageable pageable);
    Page<Order> getMyOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Order getMyCart();
    Order placeOrder();
    Order createCompleteOrder(OrderRequest orderRequest);
    Page<OrderItem> getMyCartItems(Pageable pageable);
    Order updateStatus(Long id, String status);
}
