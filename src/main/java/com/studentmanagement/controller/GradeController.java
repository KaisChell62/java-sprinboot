package com.studentmanagement.controller;

import com.studentmanagement.model.Grade;
import com.studentmanagement.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grades")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;
    
    @PostMapping
    public Grade createGrade(@Valid @RequestBody Grade grade) {
        return gradeService.createGrade(grade);
    }
    
    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable Long id, @Valid @RequestBody Grade grade) {
        return gradeService.updateGrade(id, grade);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return gradeService.getGradesByStudent(studentId);
    }
    
    @GetMapping("/course/{courseId}")
    public List<Grade> getGradesByCourse(@PathVariable Long courseId) {
        return gradeService.getGradesByCourse(courseId);
    }
    
    @GetMapping("/reports/course/{courseId}")
    public Map<String, Double> getCourseReport(@PathVariable Long courseId) {
        return gradeService.generateCourseReport(courseId);
    }
    
    @GetMapping("/reports/student/{studentId}")
    public Map<String, Double> getStudentReport(@PathVariable Long studentId) {
        return gradeService.generateStudentReport(studentId);
    }
} 