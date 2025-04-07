-- Suppression des données existantes
DELETE FROM grade;
DELETE FROM student;
DELETE FROM course;

-- Réinitialisation des auto-incréments
ALTER TABLE student AUTO_INCREMENT = 1;
ALTER TABLE course AUTO_INCREMENT = 1;
ALTER TABLE grade AUTO_INCREMENT = 1;

-- Insertion des étudiants
INSERT INTO student (nom, prenom, email) VALUES 
('Dupont', 'Jean', 'jean.dupont@email.com'),
('Martin', 'Sophie', 'sophie.martin@email.com'),
('Bernard', 'Lucas', 'lucas.bernard@email.com'),
('Petit', 'Emma', 'emma.petit@email.com'),
('Robert', 'Thomas', 'thomas.robert@email.com'),
('Dubois', 'Marie', 'marie.dubois@email.com'),
('Moreau', 'Antoine', 'antoine.moreau@email.com'),
('Laurent', 'Julie', 'julie.laurent@email.com'),
('Simon', 'Nicolas', 'nicolas.simon@email.com'),
('Michel', 'Laura', 'laura.michel@email.com');

-- Insertion des cours
INSERT INTO course (code, nom) VALUES 
('MATH101', 'Mathématiques fondamentales'),
('PHY201', 'Physique générale'),
('INFO301', 'Programmation Java'),
('CHIM101', 'Chimie organique'),
('BIO201', 'Biologie cellulaire'),
('HIST101', 'Histoire moderne'),
('ENG201', 'Anglais avancé'),
('ECO301', 'Économie internationale'),
('PHIL101', 'Philosophie'),
('STAT201', 'Statistiques appliquées'); 