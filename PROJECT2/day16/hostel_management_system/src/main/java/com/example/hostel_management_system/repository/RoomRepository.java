package com.example.hostel_management_system.repository;

import com.example.hostel_management_system.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
    List<Room> findByHostelId(Long hostelId);
    List<Room> findByStatus(Room.RoomStatus status);
    List<Room> findByRoomType(Room.RoomType roomType);
    boolean existsByRoomNumber(String roomNumber);
    
    @Query("SELECT r FROM Room r WHERE r.status = 'AVAILABLE' AND r.currentOccupancy < r.capacity")
    List<Room> findAvailableRooms();
    
    @Query("SELECT r FROM Room r WHERE r.hostel.id = ?1 AND r.status = 'AVAILABLE' AND r.currentOccupancy < r.capacity")
    List<Room> findAvailableRoomsByHostelId(Long hostelId);
}