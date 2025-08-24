package com.sr.shop_pro.dto.request;

import com.sr.shop_pro.domain.Order;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    
    private Long userId;
    private List<CartItemRequest> items;

    public Order toEntity() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        return order;
    }
}