package com.example.hostel_management_system.controller;

import com.example.hostel_management_system.domain.Room;
import com.example.hostel_management_system.dto.RoomResponseDto;
import com.example.hostel_management_system.service.RoomService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
@Import(TestSecurityConfig.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    void getAllRooms_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setRoomNumber("A101");
        room.setPricePerMonth(500.0);
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getAllRooms(any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].roomNumber").value("A101"));
    }

    @Test
    void getRoomById_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setRoomNumber("A101");
        
        when(roomService.getRoomById(1L)).thenReturn(room);

        mockMvc.perform(get("/api/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roomNumber").value("A101"));
    }

    @Test
    void getAvailableRooms_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setStatus(Room.RoomStatus.AVAILABLE);
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getAvailableRooms(any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].status").value("AVAILABLE"));
    }

    @Test
    void getRoomsByHostelId_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setHostelName("Test Hostel");
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getRoomsByHostelId(eq(1L), any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms/hostel/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].hostelName").value("Test Hostel"));
    }

    @Test
    void getRoomsByPriceRange_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setPricePerMonth(750.0);
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getRoomsByPriceRange(eq(500.0), eq(1000.0), any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms/filter/price-range")
                .param("minPrice", "500.0")
                .param("maxPrice", "1000.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].pricePerMonth").value(750.0));
    }

    @Test
    void getRoomsByCapacity_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setCapacity(2);
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getRoomsByCapacity(eq(2), any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms/filter/capacity/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].capacity").value(2));
    }

    @Test
    void getRoomsByPrefix_Success() throws Exception {
        RoomResponseDto room = new RoomResponseDto();
        room.setId(1L);
        room.setRoomNumber("A101");
        
        Page<RoomResponseDto> roomPage = new PageImpl<>(List.of(room), PageRequest.of(0, 10), 1);
        
        when(roomService.getRoomsByRoomNumberPrefix(eq("A"), any())).thenReturn(roomPage);

        mockMvc.perform(get("/api/rooms/filter/room-prefix/A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].roomNumber").value("A101"));
    }
}