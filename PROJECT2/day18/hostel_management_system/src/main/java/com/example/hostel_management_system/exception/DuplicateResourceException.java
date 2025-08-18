package com.example.hostel_management_system.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    
    public DuplicateResourceException(String resource, String field, Object value) {
        super(String.format("%s already exists with %s: %s", resource, field, value));
    }
}