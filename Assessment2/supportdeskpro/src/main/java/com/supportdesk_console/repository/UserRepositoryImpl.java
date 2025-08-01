package com.supportdesk_console.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.supportdesk_console.util.DBUtil;
import com.supportdeskpro.supportdeskpro.domain.User;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, user_name, email FROM users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT id, user_name, email FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            // insert
            String sql = "INSERT INTO users (user_name, email) VALUES (?, ?) RETURNING id";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getEmail());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        user.setId(rs.getLong(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // update
            String sql = "UPDATE users SET user_name = ?, email = ? WHERE id = ?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getEmail());
                stmt.setLong(3, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
