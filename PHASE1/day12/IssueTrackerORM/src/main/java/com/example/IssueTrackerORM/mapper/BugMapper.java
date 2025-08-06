package com.example.IssueTrackerORM.mapper;

import com.example.IssueTrackerORM.domain.Bug;
import com.example.IssueTrackerORM.dto.BugRequestDTO;
import com.example.IssueTrackerORM.dto.BugResponseDTO;

public class BugMapper {
    public static Bug toEntity(BugRequestDTO dto){
        Bug bug = new Bug();
        bug.setTitle(dto.getTitle());
        bug.setDescription(dto.getDescription());
        bug.setPriority(dto.getPriority());
        bug.setStatus(dto.getStatus());
        return bug;
    }

    public static BugResponseDTO toDTO(Bug bug){
        BugResponseDTO dto = new BugResponseDTO();
        dto.setId(bug.getId());
        dto.setTitle(bug.getTitle());
        dto.setDescription(bug.getDescription());
        dto.setPriority(bug.getPriority());
        dto.setStatus(bug.getStatus());
        dto.setProjectName(bug.getProject().getName());
        return dto;
    }
}
