package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.domain.Staff.StaffRole;
import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.dto.StudentResponseDto;
import com.example.hostel_management_system.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
@Import(TestSecurityConfig.class)
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllStaff_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setName("John Staff");
        staff.setRole(Staff.StaffRole.valueOf("WARDEN"));
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 10), 1);
        
        when(staffService.getAllStaff(any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/staff"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("John Staff"));
    }

    @Test
    void getStaffById_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setName("John Staff");
        
        when(staffService.getStaffById(1L)).thenReturn(staff);

        mockMvc.perform(get("/api/staff/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Staff"));
    }

    @Test
    void getStaffByRole_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setRole(Staff.StaffRole.valueOf("WARDEN"));
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 10), 1);
        
        when(staffService.getStaffByRole(eq(Staff.StaffRole.WARDEN), any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/staff/role/WARDEN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].role").value("WARDEN"));
    }

    @Test
    void getStudentsByStaffId_Success() throws Exception {
        StudentResponseDto student = new StudentResponseDto();
        student.setId(1L);
        student.setName("John Student");
        student.setId(1L);
        
        when(staffService.getStudentsByStaffId(1L)).thenReturn(List.of(student));

        mockMvc.perform(get("/api/staff/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].staffId").value(1));
    }

    @Test
    void getStaffByName_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setName("John Staff");
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 10), 1);
        
        when(staffService.getStaffByNameContaining(eq("John"), any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/staff/filter/name/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Staff"));
    }

    @Test
    void getStaffByEmailDomain_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setEmail("john@example.com");
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 10), 1);
        
        when(staffService.getStaffByEmailDomain(eq("example.com"), any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/staff/filter/email-domain/example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("john@example.com"));
    }

    @Test
    void getStaffWithStudents_Success() throws Exception {
        StaffResponseDto staff = new StaffResponseDto();
        staff.setId(1L);
        staff.setName("Staff with Students");
        
        Page<StaffResponseDto> staffPage = new PageImpl<>(List.of(staff), PageRequest.of(0, 10), 1);
        
        when(staffService.getStaffWithStudents(any())).thenReturn(staffPage);

        mockMvc.perform(get("/api/staff/filter/with-students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Staff with Students"));
    }

    @Test
    void createStaff_Success() throws Exception {
        Staff staff = new Staff();
        staff.setName("New Staff");
        staff.setEmail("new@example.com");
        
        StaffResponseDto response = new StaffResponseDto();
        response.setId(1L);
        response.setName("New Staff");
        
        when(staffService.createStaff(any())).thenReturn(response);

        mockMvc.perform(post("/api/staff/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(staff)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Staff"));
    }

    @Test
    void deleteStaff_Success() throws Exception {
        mockMvc.perform(delete("/api/staff/admin/1"))
                .andExpect(status().isNoContent());
    }
}