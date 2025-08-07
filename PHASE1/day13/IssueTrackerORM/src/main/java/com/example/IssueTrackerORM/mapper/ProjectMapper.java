package com.example.IssueTrackerORM.mapper;

import com.example.IssueTrackerORM.domain.Project;
import com.example.IssueTrackerORM.dto.ProjectRequestDTO;
import com.example.IssueTrackerORM.dto.ProjectResponseDTO;

public class ProjectMapper {
    public static Project toEntity(ProjectRequestDTO projectRequestDTO){
        Project project = new Project();
        project.setName(projectRequestDTO.getName());
        return project;
    }
    public static ProjectResponseDTO toDTO(Project project){
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        return projectResponseDTO;
    }
}
