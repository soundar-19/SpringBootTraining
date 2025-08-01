package com.supportdesk_console.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.supportdesk_console.util.DBUtil;
import com.supportdeskpro.supportdeskpro.domain.Ticket;
import com.supportdeskpro.supportdeskpro.domain.User;

public class TicketRepositoryImpl implements TicketRepository {

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT t.id, t.title, t.description, t.assigned_to, t.status, t.user_id, u.user_name, u.email " +
                     "FROM tickets t LEFT JOIN users u ON t.user_id = u.id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setTitle(rs.getString("title"));
                ticket.setDescription(rs.getString("description"));
                ticket.setAssignedTo(rs.getString("assigned_to"));
                ticket.setStatus(rs.getString("status"));
                Long userId = rs.getLong("user_id");
                if (userId != 0) {
                    User user = new User();
                    user.setId(userId);
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));
                    ticket.setUser(user);
                }
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        String sql = "SELECT t.id, t.title, t.description, t.assigned_to, t.status, t.user_id, u.user_name, u.email " +
                     "FROM tickets t LEFT JOIN users u ON t.user_id = u.id WHERE t.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(rs.getLong("id"));
                    ticket.setTitle(rs.getString("title"));
                    ticket.setDescription(rs.getString("description"));
                    ticket.setAssignedTo(rs.getString("assigned_to"));
                    ticket.setStatus(rs.getString("status"));
                    Long userId = rs.getLong("user_id");
                    if (userId != 0) {
                        User user = new User();
                        user.setId(userId);
                        user.setUserName(rs.getString("user_name"));
                        user.setEmail(rs.getString("email"));
                        ticket.setUser(user);
                    }
                    return Optional.of(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            // insert
            String sql = "INSERT INTO tickets (title, description, assigned_to, status, user_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ticket.getTitle());
                stmt.setString(2, ticket.getDescription());
                stmt.setString(3, ticket.getAssignedTo());
                stmt.setString(4, ticket.getStatus());
                if (ticket.getUser() != null) {
                    stmt.setLong(5, ticket.getUser().getId());
                } else {
                    stmt.setNull(5, java.sql.Types.BIGINT);
                }
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        ticket.setId(rs.getLong(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // update
            String sql = "UPDATE tickets SET title = ?, description = ?, assigned_to = ?, status = ?, user_id = ? WHERE id = ?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ticket.getTitle());
                stmt.setString(2, ticket.getDescription());
                stmt.setString(3, ticket.getAssignedTo());
                stmt.setString(4, ticket.getStatus());
                if (ticket.getUser() != null) {
                    stmt.setLong(5, ticket.getUser().getId());
                } else {
                    stmt.setNull(5, java.sql.Types.BIGINT);
                }
                stmt.setLong(6, ticket.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ticket;
    }
}
