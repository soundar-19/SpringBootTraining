package com.sr.shop_pro.dto.response;

import com.sr.shop_pro.domain.OrderItem;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.id = orderItem.getId();
        dto.productId = orderItem.getProduct().getId();
        dto.productName = orderItem.getProduct().getName();
        dto.quantity = orderItem.getQuantity();
        return dto;
    }
}