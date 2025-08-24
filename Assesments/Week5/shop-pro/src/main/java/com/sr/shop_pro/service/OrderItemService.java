package com.sr.shop_pro.service;

import com.sr.shop_pro.domain.OrderItem;
import com.sr.shop_pro.dto.request.CartItemRequest;

public interface OrderItemService {
    OrderItem create(CartItemRequest orderItemRequest);
    OrderItem updateCartItemByProductId(Long productId, Integer quantity);
    void removeFromCartByProductId(Long productId);
    void clearCart();
}
