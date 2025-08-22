package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.RoomResponseDto;
import com.example.hostel_management_system.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
    // Task 1: Multiple Sort Fields Support
    @GetMapping
    public ResponseEntity<Page<RoomResponseDto>> getAllRooms(
            @PageableDefault(size = 10, sort = {"roomNumber", "pricePerMonth"}, 
                           direction = Sort.Direction.ASC) Pageable pageable) {
        Page<RoomResponseDto> rooms = roomService.getAllRooms(pageable);
        return ResponseEntity.ok(rooms);
    }
    
    // Task 3: Search with Paging
    @GetMapping("/available")
    public ResponseEntity<Page<RoomResponseDto>> getAvailableRooms(
            @PageableDefault(size = 10, sort = "pricePerMonth") Pageable pageable) {
        Page<RoomResponseDto> availableRooms = roomService.getAvailableRooms(pageable);
        return ResponseEntity.ok(availableRooms);
    }
    
    @GetMapping("/hostel/{hostelId}")
    public ResponseEntity<Page<RoomResponseDto>> getRoomsByHostelId(
            @PathVariable Long hostelId,
            @PageableDefault(size = 10, sort = "roomNumber") Pageable pageable) {
        Page<RoomResponseDto> rooms = roomService.getRoomsByHostelId(hostelId, pageable);
        return ResponseEntity.ok(rooms);
    }
    

    @GetMapping("/filter/price-range")
    public ResponseEntity<Page<RoomResponseDto>> getRoomsByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice,
            @PageableDefault(size = 10, sort = "pricePerMonth") Pageable pageable) {
        Page<RoomResponseDto> rooms = roomService.getRoomsByPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/filter/capacity/{capacity}")
    public ResponseEntity<Page<RoomResponseDto>> getRoomsByCapacity(
            @PathVariable Integer capacity,
            @PageableDefault(size = 10, sort = "roomNumber") Pageable pageable) {
        Page<RoomResponseDto> rooms = roomService.getRoomsByCapacity(capacity, pageable);
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/filter/room-prefix/{prefix}")
    public ResponseEntity<Page<RoomResponseDto>> getRoomsByPrefix(
            @PathVariable String prefix,
            @PageableDefault(size = 10, sort = "roomNumber") Pageable pageable) {
        Page<RoomResponseDto> rooms = roomService.getRoomsByRoomNumberPrefix(prefix, pageable);
        return ResponseEntity.ok(rooms);
    }
}