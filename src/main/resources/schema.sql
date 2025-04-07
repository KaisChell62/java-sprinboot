-- Suppression des tables si elles existent
DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;

-- Création de la table student
CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Création de la table course
CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL
);

-- Création de la table grade
CREATE TABLE grade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    note DECIMAL(4,2) NOT NULL,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
); 