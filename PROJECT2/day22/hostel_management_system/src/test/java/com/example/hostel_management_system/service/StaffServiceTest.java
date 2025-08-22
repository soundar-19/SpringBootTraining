package com.example.hostel_management_system.service;

import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.dto.StaffResponseDto;
import com.example.hostel_management_system.mapper.StaffMapper;
import com.example.hostel_management_system.repository.StaffRepository;
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
class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private StaffMapper staffMapper;

    @InjectMocks
    private StaffService staffService;

    @Test
    void getAllStaff_Success() {
        Staff staff = new Staff();
        staff.setId(1L);
        staff.setName("John Staff");

        StaffResponseDto responseDto = new StaffResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Staff");

        Page<Staff> staffPage = new PageImpl<>(List.of(staff));
        Pageable pageable = PageRequest.of(0, 10);

        when(staffRepository.findAll(pageable)).thenReturn(staffPage);
        when(staffMapper.toResponseDto(staff)).thenReturn(responseDto);

        Page<StaffResponseDto> result = staffService.getAllStaff(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("John Staff", result.getContent().get(0).getName());
        verify(staffRepository).findAll(pageable);
    }

    @Test
    void getStaffById_Success() {
        Staff staff = new Staff();
        staff.setId(1L);
        staff.setName("John Staff");

        StaffResponseDto responseDto = new StaffResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Staff");

        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        when(staffMapper.toResponseDto(staff)).thenReturn(responseDto);

        StaffResponseDto result = staffService.getStaffById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Staff", result.getName());
    }

    @Test
    void getStaffByRole_Success() {
        Staff staff = new Staff();
        staff.setId(1L);
        staff.setRole(Staff.StaffRole.WARDEN);

        StaffResponseDto responseDto = new StaffResponseDto();
        responseDto.setId(1L);
        responseDto.setRole(Staff.StaffRole.WARDEN);

        Page<Staff> staffPage = new PageImpl<>(List.of(staff));
        Pageable pageable = PageRequest.of(0, 10);

        when(staffRepository.findByRole(Staff.StaffRole.WARDEN, pageable)).thenReturn(staffPage);
        when(staffMapper.toResponseDto(staff)).thenReturn(responseDto);

        Page<StaffResponseDto> result = staffService.getStaffByRole(Staff.StaffRole.WARDEN, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("WARDEN", result.getContent().get(0).getRole());
    }

    @Test
    void createStaff_Success() {
        Staff staff = new Staff();
        staff.setName("New Staff");

        Staff savedStaff = new Staff();
        savedStaff.setId(1L);
        savedStaff.setName("New Staff");

        StaffResponseDto responseDto = new StaffResponseDto();
        responseDto.setId(1L);
        responseDto.setName("New Staff");

        when(staffRepository.save(staff)).thenReturn(savedStaff);
        when(staffMapper.toResponseDto(savedStaff)).thenReturn(responseDto);

        StaffResponseDto result = staffService.createStaff(staff);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Staff", result.getName());
        verify(staffRepository).save(staff);
    }

    @Test
    void deleteStaff_Success() {
        when(staffRepository.existsById(1L)).thenReturn(true);

        staffService.deleteStaff(1L);

        verify(staffRepository).deleteById(1L);
    }
}