package com.studentmanagement.service;

import com.studentmanagement.model.Grade;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    
    public Grade createGrade(Grade grade) {
        studentRepository.findById(grade.getStudent().getId())
            .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));
        courseRepository.findById(grade.getCourse().getId())
            .orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));
        return gradeRepository.save(grade);
    }
    
    public Grade updateGrade(Long id, Grade gradeDetails) {
        Grade grade = gradeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Note non trouvée"));
        grade.setNote(gradeDetails.getNote());
        return gradeRepository.save(grade);
    }
    
    public void deleteGrade(Long id) {
        Grade grade = gradeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Note non trouvée"));
        gradeRepository.delete(grade);
    }
    
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
    
    public List<Grade> getGradesByCourse(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }
    
    public Double getAverageGradeByCourse(Long courseId) {
        return gradeRepository.getAverageGradeByCourse(courseId);
    }
    
    public Double getAverageGradeByStudent(Long studentId) {
        return gradeRepository.getAverageGradeByStudent(studentId);
    }
    
    public Map<String, Double> generateCourseReport(Long courseId) {
        Map<String, Double> report = new HashMap<>();
        Double averageGrade = getAverageGradeByCourse(courseId);
        report.put("moyenne", averageGrade != null ? averageGrade : 0.0);
        return report;
    }
    
    public Map<String, Double> generateStudentReport(Long studentId) {
        Map<String, Double> report = new HashMap<>();
        Double averageGrade = getAverageGradeByStudent(studentId);
        report.put("moyenne", averageGrade != null ? averageGrade : 0.0);
        return report;
    }
    
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Note non trouvée"));
    }
} 