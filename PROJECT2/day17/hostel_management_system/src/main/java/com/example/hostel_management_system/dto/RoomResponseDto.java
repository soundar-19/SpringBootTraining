package com.example.hostel_management_system.dto;

import com.example.hostel_management_system.domain.Room;


public class RoomResponseDto {
    private Long id;
    private String roomNumber;
    private Room.RoomType roomType;
    private Integer capacity;
    private Integer currentOccupancy;
    private Double pricePerMonth;
    private Room.RoomStatus status;
    private String hostelName;
    
    public RoomResponseDto() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public Room.RoomType getRoomType() { return roomType; }
    public void setRoomType(Room.RoomType roomType) { this.roomType = roomType; }
    
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    
    public Integer getCurrentOccupancy() { return currentOccupancy; }
    public void setCurrentOccupancy(Integer currentOccupancy) { this.currentOccupancy = currentOccupancy; }
    
    public Double getPricePerMonth() { return pricePerMonth; }
    public void setPricePerMonth(Double pricePerMonth) { this.pricePerMonth = pricePerMonth; }
    
    
    public Room.RoomStatus getStatus() { return status; }
    public void setStatus(Room.RoomStatus status) { this.status = status; }
    
    public String getHostelName() { return hostelName; }
    public void setHostelName(String hostelName) { this.hostelName = hostelName; }
}