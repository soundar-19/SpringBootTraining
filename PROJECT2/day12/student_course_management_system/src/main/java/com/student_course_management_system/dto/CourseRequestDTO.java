package com.student_course_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequestDTO {

    @NotBlank(message = "Course Code is required")
    private String courseCode;

    @NotBlank(message = "Course Title is required")
    private String courseTitle;

    @NotNull(message = "Credits is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 5, message = "Credits cannot exceed 5")
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
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
