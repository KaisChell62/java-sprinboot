package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
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

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        student = new Student();
        student.setId(1L);
        student.setNom("Dupont");
        student.setPrenom("Jean");
        student.setEmail("jean.dupont@email.com");
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        
        List<Student> students = studentService.getAllStudents();
        
        assertNotNull(students);
        assertEquals(1, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_WithValidId_ShouldReturnStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        
        Student found = studentService.getStudentById(1L);
        
        assertNotNull(found);
        assertEquals("Dupont", found.getNom());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void getStudentById_WithInvalidId_ShouldThrowException() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudentById(99L);
        });
    }

    @Test
    void createStudent_ShouldReturnSavedStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        
        Student created = studentService.createStudent(student);
        
        assertNotNull(created);
        assertEquals("Dupont", created.getNom());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void updateStudent_WithValidId_ShouldReturnUpdatedStudent() {
        Student updatedStudent = new Student();
        updatedStudent.setNom("Dupont2");
        updatedStudent.setPrenom("Jean2");
        updatedStudent.setEmail("jean2.dupont@email.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);

        assertNotNull(result);
        assertEquals("Dupont2", result.getNom());
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(any(Student.class));
    }
} 