package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StaffService;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@Import(TestSecurityConfig.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffService staffService;

    @MockBean
    private StudentService studentService;

    @Test
    void getDashboard_Success() throws Exception {
        Map<String, Object> dashboard = Map.of(
            "totalStaff", 10,
            "totalStudents", 50,
            "adminCount", 2
        );
        
        when(staffService.getAdminDashboard()).thenReturn(dashboard);

        mockMvc.perform(get("/api/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStaff").value(10))
                .andExpect(jsonPath("$.totalStudents").value(50));
    }

    @Test
    void getAllStaffForAdmin_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setName("John Doe");
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 20), 1);
        
        when(staffService.getAllStaff(any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/admin/staff/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("John Doe"));
    }

    @Test
    void getAllStudentsForAdmin_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("Jane Doe");
        
        Page<StudentResponseDto> studentPage = new PageImpl<>(List.of(student), PageRequest.of(0, 20), 1);
        
        when(studentService.getAllStudents(any())).thenReturn(studentPage);

        mockMvc.perform(get("/api/admin/students/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Jane Doe"));
    }
}