package com.buganalyticsapi.buganalyticsapi.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "project")
    private List<Bug> bugs;
    
    public Project() {}
    
    public Long getId() {
         return id; 
    }
    public void setId(Long id) {
         this.id = id; 
    }
    
    public String getName() {
         return name; 
    }
    public void setName(String name) {
         this.name = name; 
    }
    
    public List<Bug> getBugs() {
         return bugs; 
    }
    public void setBugs(List<Bug> bugs) {
         this.bugs = bugs; 
    }
}