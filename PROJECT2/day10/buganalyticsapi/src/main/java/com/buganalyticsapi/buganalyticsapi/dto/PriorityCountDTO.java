package com.buganalyticsapi.buganalyticsapi.dto;

public class PriorityCountDTO {
    private String priority;
    private Long count;
    
    public PriorityCountDTO(String priority, Long count) {
        this.priority = priority;
        this.count = count;
    }
    
    public String getPriority() { 
        return priority; 
    }
    public void setPriority(String priority) { 
        this.priority = priority; 
    }
    
    public Long getCount() {
         return count; 
    }
    public void setCount(Long count) {
         this.count = count;
     }
}