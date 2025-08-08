package com.student_course_management_system.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_course_management_system.domain.Course;
import com.student_course_management_system.repository.CourseRepository;
import com.student_course_management_system.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    public Course save(Course course){
        if(findByCourseCode(course.getCourseCode()) != null || findByCourseTitle(course.getCourseTitle()) != null) return null;
        if(course.getCredits() < 1 || course.getCredits() > 5) return null;
       return courseRepository.save(course);
    }
    public Course findById(Long courseId){
        return courseRepository.findById(courseId).orElse(null);
    }
    public Course update(Long courseId, Course course){
        if(findById(courseId) != null){
            Course existingWithCode = findByCourseCode(course.getCourseCode());
            Course existingWithTitle = findByCourseTitle(course.getCourseTitle());
            
            if((existingWithCode != null && !existingWithCode.getId().equals(courseId)) || 
               (existingWithTitle != null && !existingWithTitle.getId().equals(courseId))) {
                return null;
            }
            
            if(course.getCredits() < 1 || course.getCredits() > 5) return null;
            course.setId(courseId);
            return courseRepository.save(course);
        }else return null;
    }
    public void deleteById(Long courseId){
        if(findById(courseId) != null) courseRepository.deleteById(courseId);
    }
    public List<Course> findAll(){
        return courseRepository.findAll();
    }
    public Course findByCourseCode(String courseCode){
        return courseRepository.findByCourseCode(courseCode);
    }
    public Course findByCourseTitle(String courseTitle){
        return courseRepository.findByCourseTitle(courseTitle);
    }
    public List<Course> findByCredits(int credits){
        return courseRepository.findAllByCredits(credits);
    }
    

}
