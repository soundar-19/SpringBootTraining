package com.student_course_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student_course_management_system.domain.Enrollment;
import com.student_course_management_system.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        Enrollment enrollment = enrollmentService.save(studentId, courseId);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.findById(id);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudentId(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.findByCourseId(courseId));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkEnrollmentExists(@RequestParam Long studentId, @RequestParam Long courseId) {
        return ResponseEntity.ok(enrollmentService.existsByStudentIdAndCourseId(studentId, courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        enrollmentService.update(id, enrollment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<Void> unenrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.deleteByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}