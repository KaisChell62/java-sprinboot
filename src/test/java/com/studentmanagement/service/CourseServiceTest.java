package com.studentmanagement.service;

import com.studentmanagement.model.Course;
import com.studentmanagement.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        course = new Course();
        course.setId(1L);
        course.setNom("Mathématiques");
        course.setCode("MATH101");
    }

    @Test
    void getAllCourses_ShouldReturnListOfCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));
        
        List<Course> courses = courseService.getAllCourses();
        
        assertNotNull(courses);
        assertEquals(1, courses.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void getCourseById_WithValidId_ShouldReturnCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        Course found = courseService.getCourseById(1L);
        
        assertNotNull(found);
        assertEquals("Mathématiques", found.getNom());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void getCourseById_WithInvalidId_ShouldThrowException() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            courseService.getCourseById(99L);
        });
    }

    @Test
    void createCourse_ShouldReturnSavedCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        
        Course created = courseService.createCourse(course);
        
        assertNotNull(created);
        assertEquals("Mathématiques", created.getNom());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void updateCourse_WithValidId_ShouldReturnUpdatedCourse() {
        Course updatedCourse = new Course();
        updatedCourse.setNom("Physique");
        updatedCourse.setCode("PHYS101");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);

        Course result = courseService.updateCourse(1L, updatedCourse);

        assertNotNull(result);
        assertEquals("Physique", result.getNom());
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(any(Course.class));
    }
} 