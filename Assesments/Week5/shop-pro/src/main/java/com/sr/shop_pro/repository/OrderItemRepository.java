package com.sr.shop_pro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.shop_pro.domain.OrderItem;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrderId(Long orderId, Pageable pageable);
    Optional<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
    void deleteByOrderId(Long orderId);
    long countByOrderId(Long orderId);
}
