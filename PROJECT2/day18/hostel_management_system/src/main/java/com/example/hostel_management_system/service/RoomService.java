package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Room;
import com.example.hostel_management_system.dto.RoomResponseDto;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public RoomResponseDto getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        return convertToResponseDto(room);
    }
    
    public Page<RoomResponseDto> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable).map(this::convertToResponseDto);
    }
    
    public Page<RoomResponseDto> getRoomsByHostelId(Long hostelId, Pageable pageable) {
        return roomRepository.findByHostelId(hostelId, pageable).map(this::convertToResponseDto);
    }
    
    public Page<RoomResponseDto> getRoomsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
        return roomRepository.findByPricePerMonthBetween(minPrice, maxPrice, pageable)
                .map(this::convertToResponseDto);
    }
    
    public Page<RoomResponseDto> getRoomsByCapacity(Integer capacity, Pageable pageable) {
        return roomRepository.findByCapacity(capacity, pageable).map(this::convertToResponseDto);
    }
    
    public Page<RoomResponseDto> getRoomsByRoomNumberPrefix(String prefix, Pageable pageable) {
        return roomRepository.findByRoomNumberStartingWith(prefix, pageable)
                .map(this::convertToResponseDto);
    }
    
    private RoomResponseDto convertToResponseDto(Room room) {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setCapacity(room.getCapacity());
        dto.setCurrentOccupancy(room.getCurrentOccupancy());
        dto.setPricePerMonth(room.getPricePerMonth());
        dto.setStatus(room.getStatus());
        dto.setHostelName(room.getHostel() != null ? room.getHostel().getName() : null);
        return dto;
    }

    public Page<RoomResponseDto> getAvailableRooms(Pageable pageable) {
        return roomRepository.findAvailableRooms(pageable).map(this::convertToResponseDto);
    }
}