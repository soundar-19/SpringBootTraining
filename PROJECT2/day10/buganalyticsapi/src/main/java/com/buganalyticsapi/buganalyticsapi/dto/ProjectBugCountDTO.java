package com.buganalyticsapi.buganalyticsapi.dto;

public class ProjectBugCountDTO {
    private String projectName;
    private Long bugCount;
    
    public ProjectBugCountDTO(String projectName, Long bugCount) {
        this.projectName = projectName;
        this.bugCount = bugCount;
    }
    
    public String getProjectName() {
         return projectName;
         }
    public void setProjectName(String projectName) {
         this.projectName = projectName; 
        }
    
    public Long getBugCount() {
         return bugCount; 
        }
    public void setBugCount(Long bugCount) { 
        this.bugCount = bugCount; 
    }
}