package com.buganalyticsapi.buganalyticsapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "bugs")
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String status;
    private String priority;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    public Bug() {}
    
    public Long getId() {
         return id; 
        }
    public void setId(Long id) {
         this.id = id; 
        }
    
    public String getTitle() {
         return title; 
    }
    public void setTitle(String title) {
         this.title = title; 
    }
    
    public String getStatus() {
         return status; 
    }
    public void setStatus(String status) {
         this.status = status; 
    }
    
    public String getPriority() {
         return priority; 
    }
    public void setPriority(String priority) {
         this.priority = priority; 
    }
    
    public Project getProject() {
         return project; 
    }
    public void setProject(Project project) {
         this.project = project; 
    }
}