package com.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestion des Notes Étudiantes",
        version = "1.0",
        description = "API pour gérer les étudiants, les cours et les notes"
    )
)
public class StudentGradeManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentGradeManagementApplication.class, args);
    }
} 