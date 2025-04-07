package com.studentmanagement.controller;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.GradeService;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Gestion des étudiants
    @GetMapping("/web/students")
    public String listStudents(Model model, 
                               @RequestParam(name = "removeId", required = false) Long removeId,
                               RedirectAttributes redirectAttributes) {
        // Si un ID est fourni dans removeId, on effectue la suppression
        if (removeId != null) {
            try {
                studentService.deleteStudent(removeId);
                redirectAttributes.addFlashAttribute("successMessage", "Étudiant supprimé avec succès");
            } catch (Exception e) {
                System.out.println("Erreur lors de la suppression de l'étudiant: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la suppression: " + e.getMessage());
            }
            return "redirect:/web/students";
        }
        
        // Sinon, afficher la liste normalement
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("newStudent", new Student());
        return "students";
    }

    @PostMapping("/web/students")
    public String addStudent(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/web/students";
    }

    @GetMapping("/web/students/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/web/students/{id}")
    @ResponseBody
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/web/students/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/web/students/remove/{id}")
    public String deleteStudentGet(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage", "Étudiant supprimé avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'étudiant: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la suppression: " + e.getMessage());
        }
        return "redirect:/web/students";
    }

    // Gestion des notes d'un étudiant spécifique
    @GetMapping("/web/grades/student/{id}")
    public String studentGrades(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("grades", gradeService.getGradesByStudent(id));
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("average", gradeService.getAverageGradeByStudent(id));
        model.addAttribute("newGrade", new Grade());
        return "student-grades";
    }

    // Gestion des cours
    @GetMapping("/web/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("newCourse", new Course());
        return "courses";
    }

    @PostMapping("/web/courses")
    public String addCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/web/courses";
    }
    
    @GetMapping("/web/courses/{id}/edit")
    public String editCourseForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "edit-course";
    }
    
    @PostMapping("/web/courses/{id}/update")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.updateCourse(id, course);
        return "redirect:/web/courses";
    }
    
    @GetMapping("/web/courses/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/web/courses";
    }
    
    @GetMapping("/web/grades/course/{id}")
    public String courseGrades(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("grades", gradeService.getGradesByCourse(id));
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("newGrade", new Grade());
        return "course-grades";
    }

    // Gestion des notes
    @GetMapping("/web/grades")
    public String listGrades(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("grades", gradeService.getAllGrades());
        model.addAttribute("newGrade", new Grade());
        return "grades";
    }

    @PostMapping("/web/grades")
    public String addGrade(@ModelAttribute Grade grade, @RequestParam(required = false) Long studentId, @RequestParam(required = false) Long courseId) {
        // Si on a des IDs en paramètres de requête, les utiliser (pour la compatibilité avec les anciens formulaires)
        if (studentId != null && (grade.getStudent() == null || grade.getStudent().getId() == null)) {
            Student student = studentService.getStudentById(studentId);
            grade.setStudent(student);
        }
        
        if (courseId != null && (grade.getCourse() == null || grade.getCourse().getId() == null)) {
            Course course = courseService.getCourseById(courseId);
            grade.setCourse(course);
        }
        
        // Vérifier que les objets student et course sont correctement définis
        if (grade.getStudent() != null && grade.getStudent().getId() != null && 
            grade.getCourse() != null && grade.getCourse().getId() != null) {
            // Charger les entités complètes à partir des IDs
            Student student = studentService.getStudentById(grade.getStudent().getId());
            Course course = courseService.getCourseById(grade.getCourse().getId());
            
            grade.setStudent(student);
            grade.setCourse(course);
            
            gradeService.createGrade(grade);
            
            // Rediriger vers la page de l'étudiant si on vient de cette page
            if (grade.getStudent() != null && grade.getStudent().getId() != null) {
                return "redirect:/web/grades/student/" + grade.getStudent().getId();
            }
        }
        
        return "redirect:/web/grades";
    }

    @GetMapping("/web/grades/{id}")
    @ResponseBody
    public Grade getGrade(@PathVariable Long id) {
        return gradeService.getGradeById(id);
    }

    @PutMapping("/web/grades/{id}")
    @ResponseBody
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/web/grades/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }

    // Page des rapports
    @GetMapping("/web/reports")
    public String reports(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "reports";
    }
    
    @GetMapping("/web/reports/view-course")
    public String courseReport(@RequestParam Long courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("grades", gradeService.getGradesByCourse(courseId));
        model.addAttribute("average", gradeService.getAverageGradeByCourse(courseId));
        model.addAttribute("report", gradeService.generateCourseReport(courseId));
        return "course-report";
    }
    
    @GetMapping("/web/reports/view-student")
    public String studentReport(@RequestParam Long studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("grades", gradeService.getGradesByStudent(studentId));
        model.addAttribute("average", gradeService.getAverageGradeByStudent(studentId));
        model.addAttribute("report", gradeService.generateStudentReport(studentId));
        return "student-report";
    }
} 