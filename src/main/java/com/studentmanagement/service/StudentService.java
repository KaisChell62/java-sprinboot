package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + id));
    }
    
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setNom(studentDetails.getNom());
        student.setPrenom(studentDetails.getPrenom());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }
    
    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        
        try {
            // Supprimer d'abord toutes les notes associées à cet étudiant
            gradeRepository.deleteByStudentId(id);
            
            // Puis supprimer l'étudiant
            studentRepository.delete(student);
        } catch (Exception e) {
            throw new RuntimeException("Impossible de supprimer l'étudiant : " + e.getMessage(), e);
        }
    }
} 