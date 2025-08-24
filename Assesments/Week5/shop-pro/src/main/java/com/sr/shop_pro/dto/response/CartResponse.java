package com.sr.shop_pro.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;
    private Integer totalItems;
    
    @Data
    public static class CartItemResponse {
        private Long productId;
        private String productName;
        private BigDecimal unitPrice;
        private Integer quantity;
        private BigDecimal totalPrice;
    }
}