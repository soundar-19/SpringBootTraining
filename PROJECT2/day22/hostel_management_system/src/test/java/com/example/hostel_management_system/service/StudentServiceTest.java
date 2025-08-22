package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.mapper.StudentMapper;
import com.example.hostel_management_system.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents_Success() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Doe");

        Page<Student> studentPage = new PageImpl<>(List.of(student));
        Pageable pageable = PageRequest.of(0, 10);

        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        Page<StudentResponseDto> result = studentService.getAllStudents(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getName());
        verify(studentRepository).findAll(pageable);
    }

    @Test
    void getStudentById_Success() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toResponseDto(student)).thenReturn(responseDto);

        StudentResponseDto result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void createStudent_Success() {
        Student student = new Student();
        student.setName("New Student");

        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setName("New Student");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setId(1L);
        responseDto.setName("New Student");

        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toResponseDto(savedStudent)).thenReturn(responseDto);

        StudentResponseDto result = studentService.createStudent(student);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Student", result.getName());
        verify(studentRepository).save(student);
    }

    @Test
    void deleteStudent_Success() {
        when(studentRepository.existsById(1L)).thenReturn(true);

        studentService.deleteStudent(1L);

        verify(studentRepository).deleteById(1L);
    }
}