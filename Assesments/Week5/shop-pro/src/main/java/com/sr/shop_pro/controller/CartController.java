package com.sr.shop_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.dto.response.OrderItemResponseDTO;
import com.sr.shop_pro.dto.response.OrderResponse;
import com.sr.shop_pro.service.OrderItemService;
import com.sr.shop_pro.service.OrderService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Shopping cart management APIs")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class CartController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @Operation(summary = "Get cart summary", description = "Get current user's cart information")
    public ResponseEntity<OrderResponse> getCart() {
        return ResponseEntity.ok(OrderResponse.toDTO(orderService.getMyCart()));
    }

    @GetMapping("/items")
    @Operation(summary = "Get cart items", description = "Get all items in current user's cart")
    public ResponseEntity<Page<OrderItemResponseDTO>> getCartItems(Pageable pageable) {
        Page<OrderItemResponseDTO> cartItems = orderService.getMyCartItems(pageable)
            .map(OrderItemResponseDTO::toDTO);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/items")
    @Operation(summary = "Add item to cart", description = "Add a product to the cart")
    public ResponseEntity<OrderItemResponseDTO> addItemToCart(@Valid @RequestBody CartItemRequest itemRequest) {
        var orderItem = orderItemService.create(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderItemResponseDTO.toDTO(orderItem));
    }

    @PutMapping("/items/{productId}")
    @Operation(summary = "Update cart item quantity", description = "Update quantity of a specific product in cart")
    public ResponseEntity<OrderItemResponseDTO> updateCartItem(
            @PathVariable Long productId, 
            @RequestParam Integer quantity) {
        var orderItem = orderItemService.updateCartItemByProductId(productId, quantity);
        return ResponseEntity.ok(OrderItemResponseDTO.toDTO(orderItem));
    }

    @DeleteMapping("/items/{productId}")
    @Operation(summary = "Remove item from cart", description = "Remove a specific product from cart")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long productId) {
        orderItemService.removeFromCartByProductId(productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    @Operation(summary = "Checkout cart", description = "Convert cart to order and place it")
    public ResponseEntity<OrderResponse> checkout() {
        return ResponseEntity.ok(OrderResponse.toDTO(orderService.placeOrder()));
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Clear cart", description = "Remove all items from cart")
    public ResponseEntity<Void> clearCart() {
        orderItemService.clearCart();
        return ResponseEntity.noContent().build();
    }
}