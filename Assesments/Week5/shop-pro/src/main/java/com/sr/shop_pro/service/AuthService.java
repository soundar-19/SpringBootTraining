package com.sr.shop_pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.OrderRepository;
import com.sr.shop_pro.repository.UserRepository;
import java.time.LocalDateTime;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
        
        ensureUserHasCart(user);
        
        return user;
    }
    
    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
            .getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }
    
    private void ensureUserHasCart(User user) {
        if (user.getRole().toString().equals("CUSTOMER") && 
            !orderRepository.findByUserIdAndStatus(user.getId(), "CART").isPresent()) {
            Order cart = new Order();
            cart.setUser(user);
            cart.setStatus("CART");
            cart.setOrderDate(LocalDateTime.now());
            orderRepository.save(cart);
        }
    }
}