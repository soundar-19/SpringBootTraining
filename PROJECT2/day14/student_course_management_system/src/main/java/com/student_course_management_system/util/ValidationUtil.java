package com.student_course_management_system.util;

import com.student_course_management_system.exception.ValidationException;

public class ValidationUtil {
    
    public static void validatePositiveId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new ValidationException(fieldName + " must be a positive number");
        }
    }
    
    public static void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }
    
    public static void validateStringNotBlank(String str, String fieldName) {
        if (str == null || str.trim().isEmpty()) {
            throw new ValidationException(fieldName + " cannot be blank");
        }
    }
    
    public static void validateCourseCode(String courseCode) {
        if (courseCode == null || !courseCode.matches("^[A-Z]{2,4}[0-9]{3,6}$")) {
            throw new ValidationException("Course code must follow format: 2-4 uppercase letters followed by 3-6 digits (e.g., CS101, MATH1001)");
        }
    }
    
    public static void validateCredits(Integer credits) {
        if (credits == null || credits < 1 || credits > 6) {
            throw new ValidationException("Credits must be between 1 and 6");
        }
    }
    
    public static void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new ValidationException("Please provide a valid email address");
        }
    }
    
    public static void validateRollNumber(Long rollNumber) {
        if (rollNumber == null || rollNumber <= 0 || rollNumber > 999999999L) {
            throw new ValidationException("Roll number must be positive and cannot exceed 9 digits");
        }
    }
}