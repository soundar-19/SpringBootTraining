package com.sr.shop_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sr.shop_pro.dto.request.OrderRequest;
import com.sr.shop_pro.dto.response.OrderResponse;
import com.sr.shop_pro.service.OrderService;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Order management APIs")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Admin: View all orders in system")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
        Page<OrderResponse> orders = orderService.findAll(pageable)
            .map(OrderResponse::toDTO);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my-orders")
    @Operation(summary = "Get my orders", description = "Get current user's order history")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Page<OrderResponse>> getMyOrders(Pageable pageable) {
        Page<OrderResponse> orders = orderService.getMyOrders(pageable)
            .map(OrderResponse::toDTO);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my-orders/date-range")
    @Operation(summary = "Get my orders by date range", description = "Filter user's orders by date range")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Page<OrderResponse>> getMyOrdersByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate,
            Pageable pageable) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        Page<OrderResponse> orders = orderService.getMyOrdersByDateRange(start, end, pageable)
            .map(OrderResponse::toDTO);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Get specific order details")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(OrderResponse.toDTO(order));
    }

    @PostMapping("/complete")
    @Operation(summary = "Create complete order", description = "Create order with items in one request")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<OrderResponse> createCompleteOrder(@Valid @RequestBody OrderRequest orderRequest) {
        var order = orderService.createCompleteOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.toDTO(order));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update order status", description = "Admin: Update order status (e.g., SHIPPED, DELIVERED)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        var order = orderService.updateStatus(id, status);
        return ResponseEntity.ok(OrderResponse.toDTO(order));
    }
}