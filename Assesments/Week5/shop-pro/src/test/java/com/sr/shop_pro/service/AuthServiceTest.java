package com.sr.shop_pro.service;

import com.sr.shop_pro.domain.Order;
import com.sr.shop_pro.domain.Role;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.exception.ResourceNotFoundException;
import com.sr.shop_pro.repository.OrderRepository;
import com.sr.shop_pro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private Authentication authentication;
    
    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AuthService authService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setRole(Role.CUSTOMER);
        
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetCurrentUser_Success() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        User result = authService.getCurrentUser();

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository).findByUsername("testuser");
        verify(orderRepository).save(any(Order.class)); // Cart creation
    }

    @Test
    void testGetCurrentUser_NotFound() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.getCurrentUser());
        verify(userRepository).findByUsername("testuser");
    }

    @Test
    void testGetCurrentUser_AdminNoCartCreation() {
        User adminUser = new User();
        adminUser.setId(2L);
        adminUser.setUsername("admin");
        adminUser.setRole(Role.ADMIN);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(adminUser));

        User result = authService.getCurrentUser();

        assertNotNull(result);
        assertEquals("admin", result.getUsername());
        assertEquals(Role.ADMIN, result.getRole());
        verify(orderRepository, never()).save(any(Order.class)); // No cart for admin
    }

    @Test
    void testIsAdmin_True() {
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        boolean result = authService.isAdmin();

        assertTrue(result);
    }

    @Test
    void testIsAdmin_False() {
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        boolean result = authService.isAdmin();

        assertFalse(result);
    }

    @Test
    void testEnsureUserHasCart_ExistingCart() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(orderRepository.findByUserIdAndStatus(1L, "CART")).thenReturn(Optional.of(new Order()));

        User result = authService.getCurrentUser();

        assertNotNull(result);
        verify(orderRepository, never()).save(any(Order.class)); // Cart already exists
    }
}