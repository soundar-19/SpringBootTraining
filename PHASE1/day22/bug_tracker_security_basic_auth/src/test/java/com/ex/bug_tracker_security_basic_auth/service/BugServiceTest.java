package com.ex.bug_tracker_security_basic_auth.service;

import com.ex.bug_tracker_security_basic_auth.entity.Bug;
import com.ex.bug_tracker_security_basic_auth.repository.BugRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import com.ex.bug_tracker_security_basic_auth.dto.BugResponseDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BugServiceTest {
    @Mock 
    private BugRepository bugRepository;

    @InjectMocks
    private BugService bugService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCreateBug(){
        Bug bug = new Bug();
        bug.setTitle("Login issue");
        bug.setStatus("Open");
        when(bugRepository.save(bug)).thenReturn(bug);
        Bug savedBug = bugService.createBug(bug);
        assertNotNull(savedBug);
        assertEquals("Login issue", savedBug.getTitle());
        verify(bugRepository,times(1)).save(bug);
    }
    @Test
    void testGetAllBugs(){
        Bug bug1 = new Bug();
        Bug bug2 = new Bug();
        bug1.setId(1L);
        bug1.setTitle("Issue 1");
        bug2.setId(2L);
        bug2.setTitle("Issue 2");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bug> bugPage = new PageImpl<>(Arrays.asList(bug1, bug2));
        when(bugRepository.findAll(pageable)).thenReturn(bugPage);
        Page<BugResponseDTO> bugs = bugService.getAllBugs(pageable);
        assertEquals(2, bugs.getContent().size());
        assertEquals("Issue 1", bugs.getContent().get(0).getTitle());
        assertEquals("Issue 2", bugs.getContent().get(1).getTitle());
        verify(bugRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetBugById(){
        Bug bug = new Bug();
        bug.setId(1L);
        bug.setTitle("Test Bug");
        bug.setStatus("Open");
        when(bugRepository.findById(1L)).thenReturn(Optional.of(bug));
        BugResponseDTO result = bugService.getBugById(1L);
        assertNotNull(result);
        assertEquals("Test Bug", result.getTitle());
        verify(bugRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateBug(){
        Bug existingBug = new Bug();
        existingBug.setId(1L);
        existingBug.setTitle("Old Title");
        Bug updatedBug = new Bug();
        updatedBug.setTitle("New Title");
        updatedBug.setStatus("Closed");
        when(bugRepository.findById(1L)).thenReturn(Optional.of(existingBug));
        when(bugRepository.save(any(Bug.class))).thenReturn(existingBug);
        Bug result = bugService.updateBug(1L, updatedBug);
        assertEquals("New Title", result.getTitle());
        verify(bugRepository, times(1)).findById(1L);
        verify(bugRepository, times(1)).save(existingBug);
    }

    @Test
    void testDeleteBug(){
        bugService.deleteBug(1L);
        verify(bugRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateBugStatus(){
        Bug bug = new Bug();
        bug.setId(1L);
        bug.setStatus("Open");
        when(bugRepository.findById(1L)).thenReturn(Optional.of(bug));
        when(bugRepository.save(any(Bug.class))).thenReturn(bug);
        Bug result = bugService.updateBugStatus(1L, "Closed");
        assertEquals("Closed", result.getStatus());
        verify(bugRepository, times(1)).findById(1L);
        verify(bugRepository, times(1)).save(bug);
    }

    @Test
    void testFilterBugs(){
        Bug bug1 = new Bug();
        bug1.setId(1L);
        bug1.setTitle("Bug 1");
        bug1.setStatus("Open");
        bug1.setAssignee("John");
        bug1.setProject("Project A");
        when(bugRepository.findAll()).thenReturn(Arrays.asList(bug1));
        List<BugResponseDTO> result = bugService.filterBugs("Open", "John", "Project A");
        assertEquals(1, result.size());
        assertEquals("Bug 1", result.get(0).getTitle());
        verify(bugRepository, times(1)).findAll();
    }

    @Test
    void testGetBugsMetadata(){
        Bug bug = new Bug();
        bug.setId(1L);
        bug.setTitle("Test Bug");
        bug.setStatus("Open");
        when(bugRepository.findAll()).thenReturn(Arrays.asList(bug));
        Pageable pageable = PageRequest.of(0, 10);
        Map<String, Object> metadata = bugService.getBugsMetadata("Open", null, null, pageable);
        assertNotNull(metadata);
        assertEquals(1L, metadata.get("totalElements"));
        assertEquals(1, metadata.get("totalPages"));
        verify(bugRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBugsSlice(){
        Bug bug = new Bug();
        bug.setId(1L);
        bug.setTitle("Test Bug");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bug> bugPage = new PageImpl<>(Arrays.asList(bug));
        when(bugRepository.findAll(pageable)).thenReturn(bugPage);
        Slice<BugResponseDTO> result = bugService.getAllBugsSlice(pageable);
        assertEquals(1, result.getContent().size());
        assertEquals("Test Bug", result.getContent().get(0).getTitle());
        verify(bugRepository, times(1)).findAll(pageable);
    }
}