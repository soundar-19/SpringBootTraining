package com.sr.shop_pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sr.shop_pro.domain.User;
import com.sr.shop_pro.dto.response.UserResponse;

public interface UserService {
    UserResponse findById(Long id);
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse save(User user);
    UserResponse update(Long id, User user);
    void deleteById(Long id);
}
