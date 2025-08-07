package com.student_course_management_system.dto;

import jakarta.validation.constraints.*;

public class CourseRequestDTO {

    @NotBlank(message = "Course code is required")
    @Size(min = 3, max = 10, message = "Course code must be between 3 and 10 characters")
    private String courseCode;

    @NotBlank(message = "Course title is required")
    @Size(min = 3, max = 100, message = "Course title must be between 3 and 100 characters")
    private String courseTitle;

    @NotNull(message = "Credits is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 6, message = "Credits cannot exceed 6")
    private Integer credits;

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode != null ? courseCode.trim().toUpperCase() : null;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle != null ? courseTitle.trim() : null;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
