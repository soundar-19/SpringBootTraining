package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Hostel;
import com.example.hostel_management_system.service.HostelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HostelController.class)
@Import(TestSecurityConfig.class)
class HostelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HostelService hostelService;

    @Test
    void getAllHostels_Success() throws Exception {
        Hostel hostel = new Hostel("Test Hostel", "Test Address", Hostel.HostelType.BOYS);
        hostel.setId(1L);
        
        when(hostelService.getAllHostels()).thenReturn(List.of(hostel));

        mockMvc.perform(get("/api/hostels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Hostel"));
    }

    @Test
    void getHostelById_Success() throws Exception {
        Hostel hostel = new Hostel("Test Hostel", "Test Address", Hostel.HostelType.BOYS);
        hostel.setId(1L);
        
        when(hostelService.getHostelById(1L)).thenReturn(hostel);

        mockMvc.perform(get("/api/hostels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Hostel"));
    }

    @Test
    void getHostelsByType_Success() throws Exception {
        Hostel hostel = new Hostel("Boys Hostel", "Test Address", Hostel.HostelType.BOYS);
        hostel.setId(1L);
        
        when(hostelService.getHostelsByType(Hostel.HostelType.BOYS)).thenReturn(List.of(hostel));

        mockMvc.perform(get("/api/hostels/type/BOYS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hostelType").value("BOYS"));
    }
}