package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@Import(TestSecurityConfig.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllStudents_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 5), 1);
        
        when(studentService.getAllStudents(any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void getStudentById_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Student"));
    }

    @Test
    void getStudentsByName_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.getStudentsByNameContaining(eq("John"), any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/filter/name/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void getStudentsByEmailDomain_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setEmail("john@example.com");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.getStudentsByEmailDomain(eq("example.com"), any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/filter/email-domain/example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("john@example.com"));
    }

    @Test
    void getStudentsByCity_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setAddress("123 Main St, New York");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.getStudentsByAddressCity(eq("New York"), any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/filter/city/New York"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].address").value("123 Main St, New York"));
    }

    @Test
    void filterStudents_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.filterStudents(eq("John"), eq(null), eq(null), eq(null), any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/filter")
                .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void getStudentsWithoutStaff_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("Unassigned Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.getStudentsWithoutStaff(any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/filter/without-staff"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Unassigned Student"));
    }

    @Test
    void getStudentsMetadata_Success() throws Exception {
        Map<String, Object> metadata = Map.of(
            "totalElements", 100L,
            "totalPages", 10,
            "currentPage", 0
        );
        
        when(studentService.getStudentsMetadata(any())).thenReturn(metadata);

        mockMvc.perform(get("/api/students/metadata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(100))
                .andExpect(jsonPath("$.totalPages").value(10));
    }

    @Test
    void getStudentsSortedNullsLast_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        
        when(studentService.getStudentsSortedNullsLast(any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/students/sorted-nulls-last"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void getStudentsSlice_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        
        Slice<StudentResponseDto> studentSlice = new SliceImpl<>(List.of(student), PageRequest.of(0, 10), true);
        
        when(studentService.getStudentsSlice(any())).thenReturn(studentSlice);

        mockMvc.perform(get("/api/students/slice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Student"));
    }

    @Test
    void createStudent_Success() throws Exception {
        Student student = new Student();
        student.setName("New Student");
        student.setEmail("new@example.com");
        
        StudentResponseDto response = new StudentResponseDto();
        response.setId(1L);
        response.setName("New Student");
        
        when(studentService.createStudent(any())).thenReturn(response);

        mockMvc.perform(post("/api/students/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Student"));
    }

    @Test
    void deleteStudent_Success() throws Exception {
        mockMvc.perform(delete("/api/students/admin/1"))
                .andExpect(status().isNoContent());
    }
}