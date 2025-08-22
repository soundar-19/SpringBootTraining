package com.example.hostel_management_system.exception;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(String roomNumber) {
        super(String.format("Room %s is not available for booking", roomNumber));
    }
}