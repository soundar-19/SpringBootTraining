package com.sr.shop_pro.dto.response;

import com.sr.shop_pro.domain.Order;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private UserResponse user;
    private List<OrderItemResponseDTO> items;

    public static OrderResponse toDTO(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.id = order.getId();
        dto.orderDate = order.getOrderDate();
        dto.status = order.getStatus();
        dto.user = UserResponse.toDTO(order.getUser());
        if (order.getOrderItems() != null) {
            dto.items = order.getOrderItems().stream()
                .map(OrderItemResponseDTO::toDTO)
                .toList();
        }
        return dto;
    }
}