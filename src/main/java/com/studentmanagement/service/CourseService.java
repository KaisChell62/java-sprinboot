package com.studentmanagement.service;

import com.studentmanagement.model.Course;
import com.studentmanagement.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cours non trouvé avec l'ID : " + id));
    }
    
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setNom(courseDetails.getNom());
        course.setCode(courseDetails.getCode());
        return courseRepository.save(course);
    }
    
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
} 