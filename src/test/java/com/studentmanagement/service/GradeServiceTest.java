package com.studentmanagement.service;

import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;
import com.studentmanagement.model.Course;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.repository.StudentRepository;
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

class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private GradeService gradeService;

    private Grade grade;
    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        student = new Student();
        student.setId(1L);
        student.setNom("Dupont");
        
        course = new Course();
        course.setId(1L);
        course.setNom("Mathématiques");
        
        grade = new Grade();
        grade.setId(1L);
        grade.setNote(15.0);
        grade.setStudent(student);
        grade.setCourse(course);
    }

    @Test
    void getAllGrades_ShouldReturnListOfGrades() {
        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade));
        
        List<Grade> grades = gradeService.getAllGrades();
        
        assertNotNull(grades);
        assertEquals(1, grades.size());
        verify(gradeRepository, times(1)).findAll();
    }

    @Test
    void createGrade_ShouldReturnSavedGrade() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);
        
        Grade created = gradeService.createGrade(grade);
        
        assertNotNull(created);
        assertEquals(15.0, created.getNote());
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void updateGrade_WithValidId_ShouldReturnUpdatedGrade() {
        Grade updatedGrade = new Grade();
        updatedGrade.setNote(18.0);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(updatedGrade);

        Grade result = gradeService.updateGrade(1L, updatedGrade);

        assertNotNull(result);
        assertEquals(18.0, result.getNote());
        verify(gradeRepository, times(1)).findById(1L);
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void deleteGrade_WithValidId_ShouldDeleteGrade() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        gradeService.deleteGrade(1L);

        verify(gradeRepository, times(1)).delete(grade);
    }

    @Test
    void getGradesByStudent_ShouldReturnListOfGrades() {
        when(gradeRepository.findByStudentId(1L)).thenReturn(Arrays.asList(grade));

        List<Grade> grades = gradeService.getGradesByStudent(1L);

        assertNotNull(grades);
        assertEquals(1, grades.size());
        verify(gradeRepository, times(1)).findByStudentId(1L);
    }

    @Test
    void getGradesByCourse_ShouldReturnListOfGrades() {
        when(gradeRepository.findByCourseId(1L)).thenReturn(Arrays.asList(grade));

        List<Grade> grades = gradeService.getGradesByCourse(1L);

        assertNotNull(grades);
        assertEquals(1, grades.size());
        verify(gradeRepository, times(1)).findByCourseId(1L);
    }
} 