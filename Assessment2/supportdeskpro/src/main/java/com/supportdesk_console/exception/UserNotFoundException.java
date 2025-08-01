package com.supportdesk_console.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
