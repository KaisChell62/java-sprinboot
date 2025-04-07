package com.studentmanagement.repository;

import com.studentmanagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByCourseId(Long courseId);
    
    @Query("SELECT AVG(g.note) FROM Grade g WHERE g.course.id = :courseId")
    Double getAverageGradeByCourse(@Param("courseId") Long courseId);
    
    @Query("SELECT AVG(g.note) FROM Grade g WHERE g.student.id = :studentId")
    Double getAverageGradeByStudent(@Param("studentId") Long studentId);
    
    @Transactional
    void deleteByStudentId(Long studentId);
} 