package com.sr.shop_pro.service;

import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.dto.response.CartResponse;

public interface CartService {
    CartResponse getCart(String username);
    CartResponse addItemToCart(String username, CartItemRequest request);
    CartResponse updateCartItem(String username, Long productId, Integer quantity);
    void removeItemFromCart(String username, Long productId);
    void clearCart(String username);
    Long checkoutCart(String username);
}