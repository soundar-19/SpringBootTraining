package com.sr.shop_pro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.OrderItem;
import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.dto.request.CartItemRequest;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.OrderItemRepository;
import com.sr.shop_pro.repository.ProductRepository;
import com.sr.shop_pro.service.OrderItemService;
import com.sr.shop_pro.service.OrderService;


@Service
public class OrderItemServiceImpl implements OrderItemService {
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    

    @Override
    public OrderItem create(CartItemRequest orderItemRequest) {
        Product product = productRepository.findById(orderItemRequest.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemRequest.getProductId()));
        
        Order cart = orderService.getMyCart();
        
        // Check if product already exists in cart
        var existingItem = orderItemRepository.findByOrderIdAndProductId(cart.getId(), orderItemRequest.getProductId());
        if (existingItem.isPresent()) {
            // Update quantity instead of creating duplicate
            OrderItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + orderItemRequest.getQuantity());
            return orderItemRepository.save(item);
        }
        
        // Create new cart item
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(cart);
        orderItem.setQuantity(orderItemRequest.getQuantity());
        return orderItemRepository.save(orderItem);
    }



    @Override
    public OrderItem updateCartItemByProductId(Long productId, Integer quantity) {
        Order cart = orderService.getMyCart();
        OrderItem orderItem = orderItemRepository.findByOrderIdAndProductId(cart.getId(), productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart"));
        
        orderItem.setQuantity(quantity);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void removeFromCartByProductId(Long productId) {
        Order cart = orderService.getMyCart();
        OrderItem orderItem = orderItemRepository.findByOrderIdAndProductId(cart.getId(), productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart"));
        
        orderItemRepository.delete(orderItem);
    }

    @Override
    @Transactional
    public void clearCart() {
        Order cart = orderService.getMyCart();
        orderItemRepository.deleteByOrderId(cart.getId());
    }
}
