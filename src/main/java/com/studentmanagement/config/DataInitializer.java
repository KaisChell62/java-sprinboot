package com.studentmanagement.config;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si des données existent déjà
        if (courseRepository.count() == 0) {
            initializeData();
        }
    }
    
    private void initializeData() {
        // Créer des cours de test
        Course math = new Course();
        math.setNom("Mathématiques");
        math.setCode("MATH101");
        courseRepository.save(math);
        
        Course physics = new Course();
        physics.setNom("Physique");
        physics.setCode("PHYS101");
        courseRepository.save(physics);
        
        Course history = new Course();
        history.setNom("Histoire");
        history.setCode("HIST101");
        courseRepository.save(history);
        
        // Créer des étudiants de test
        Student student1 = new Student();
        student1.setNom("Dupont");
        student1.setPrenom("Jean");
        student1.setEmail("jean.dupont@email.com");
        studentRepository.save(student1);
        
        Student student2 = new Student();
        student2.setNom("Martin");
        student2.setPrenom("Sophie");
        student2.setEmail("sophie.martin@email.com");
        studentRepository.save(student2);
        
        // Ajouter un troisième étudiant - Kais Chelhaoui
        Student student3 = new Student();
        student3.setNom("Chelhaoui");
        student3.setPrenom("Kais");
        student3.setEmail("kais.chelhaoui@next-u.fr");
        studentRepository.save(student3);
        
        // Créer des notes de test
        Grade grade1 = new Grade();
        grade1.setNote(15.5);
        grade1.setCourse(math);
        grade1.setStudent(student1);
        gradeRepository.save(grade1);
        
        Grade grade2 = new Grade();
        grade2.setNote(17.0);
        grade2.setCourse(physics);
        grade2.setStudent(student1);
        gradeRepository.save(grade2);
        
        Grade grade3 = new Grade();
        grade3.setNote(14.0);
        grade3.setCourse(math);
        grade3.setStudent(student2);
        gradeRepository.save(grade3);
        
        // Ajouter une note pour le troisième étudiant
        Grade grade4 = new Grade();
        grade4.setNote(16.0);
        grade4.setCourse(history);
        grade4.setStudent(student3);
        gradeRepository.save(grade4);
        
        System.out.println("Base de données initialisée avec des données de test");
    }
} 