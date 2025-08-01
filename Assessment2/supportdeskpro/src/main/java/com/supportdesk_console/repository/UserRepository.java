package com.supportdesk_console.repository;

import java.util.List;
import java.util.Optional;

import com.supportdeskpro.supportdeskpro.domain.User;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
