package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Room;
import com.example.hostel_management_system.dto.RoomResponseDto;
import com.example.hostel_management_system.exception.ResourceNotFoundException;
import com.example.hostel_management_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public RoomResponseDto getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        return convertToResponseDto(room);
    }
    
    public List<RoomResponseDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<RoomResponseDto> getRoomsByHostelId(Long hostelId) {
        return roomRepository.findByHostelId(hostelId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<RoomResponseDto> getRoomsByPriceRange(Double minPrice, Double maxPrice) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getPricePerMonth() >= minPrice && room.getPricePerMonth() <= maxPrice)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<RoomResponseDto> getRoomsByCapacity(Integer capacity) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getCapacity().equals(capacity))
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<RoomResponseDto> getRoomsByRoomNumberPrefix(String prefix) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getRoomNumber().startsWith(prefix))
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
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

    public List<RoomResponseDto> getAvailableRooms() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getCurrentOccupancy() < room.getCapacity())
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}