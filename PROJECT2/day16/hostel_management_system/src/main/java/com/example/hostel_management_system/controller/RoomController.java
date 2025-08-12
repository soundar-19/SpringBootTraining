package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.RoomResponseDto;
import com.example.hostel_management_system.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoomById(@PathVariable Long id) {
        RoomResponseDto responseDto = roomService.getRoomById(id);
        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAllRooms() {
        List<RoomResponseDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<RoomResponseDto>> getAvailableRooms() {
        List<RoomResponseDto> availableRooms = roomService.getAvailableRooms();
        return ResponseEntity.ok(availableRooms);
    }
    
    @GetMapping("/hostel/{hostelId}")
    public ResponseEntity<List<RoomResponseDto>> getRoomsByHostelId(@PathVariable Long hostelId) {
        List<RoomResponseDto> rooms = roomService.getRoomsByHostelId(hostelId);
        return ResponseEntity.ok(rooms);
    }
    

    @GetMapping("/filter/price-range")
    public ResponseEntity<List<RoomResponseDto>> getRoomsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<RoomResponseDto> rooms = roomService.getRoomsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/filter/capacity/{capacity}")
    public ResponseEntity<List<RoomResponseDto>> getRoomsByCapacity(@PathVariable Integer capacity) {
        List<RoomResponseDto> rooms = roomService.getRoomsByCapacity(capacity);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/filter/room-prefix/{prefix}")
    public ResponseEntity<List<RoomResponseDto>> getRoomsByPrefix(@PathVariable String prefix) {
        List<RoomResponseDto> rooms = roomService.getRoomsByRoomNumberPrefix(prefix);
        return ResponseEntity.ok(rooms);
    }
}