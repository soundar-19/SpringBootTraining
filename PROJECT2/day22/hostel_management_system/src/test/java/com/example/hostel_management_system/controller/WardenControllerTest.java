package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WardenController.class)
@Import(TestSecurityConfig.class)
class WardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudentsUnderWarden_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 15), 1);
        
        when(studentService.getAllStudents(any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/warden/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void assignStaffToStudent_Success() throws Exception {
        StudentResponseDto updatedStudent = new StudentResponseDto();
        updatedStudent.setId(1L);
        updatedStudent.setName("John Student");
        updatedStudent.setId(2L);
        
        when(studentService.assignStaffToStudent(eq(1L), eq(2L))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/warden/students/1/assign-staff")
                .param("staffId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.staffId").value(2));
    }
}