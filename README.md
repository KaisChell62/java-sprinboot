# API de Gestion des Notes Étudiantes

## 📝 Description

Cette API a été conçue pour permettre à des enseignants de gérer les notes des étudiants pour différents cours.

Elle permet :
- L’ajout, la mise à jour, la suppression et la récupération d’**étudiants**
- L’ajout, la mise à jour, la suppression et la récupération de **cours**
- L’enregistrement et la gestion des **notes**
- La génération de **rapports** sur les moyennes

Le projet respecte l’architecture REST et utilise la base de données **MySQL**.

---

## ⚙️ Fonctionnalités

### 1. Gestion des Étudiants
- Ajouter / modifier / supprimer un étudiant
- Récupérer la liste des étudiants
- Récupérer un étudiant par son ID

### 2. Gestion des Cours
- Ajouter / modifier / supprimer un cours
- Récupérer la liste des cours
- Récupérer un cours par son ID

### 3. Gestion des Notes
- Enregistrer une note pour un étudiant dans un cours
- Modifier ou supprimer une note existante
- Récupérer les notes d’un étudiant pour un cours
- Récupérer toutes les notes d’un étudiant
- Récupérer toutes les notes d’un cours

### 4. Génération de Rapports
- Moyenne des notes par cours
- Moyenne des notes par étudiant

---

## 🛠️ Technologies utilisées

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL (ou MariaDB)
- Lombok (optionnel)
- Validation avec annotations (`@Valid`, etc.)

---

## 🚀 Installation du projet

### Prérequis :
- Java JDK installé
- MySQL installé et lancé
- Maven (si non intégré à l'IDE)

### Étapes :

1. Cloner le projet :
```bash
git clone <URL_DU_REPO>
cd api-gestion-notes
```

2. Créer une base de données :
```sql
CREATE DATABASE gestion_notes;
```

3. Configurer `src/main/resources/application.properties` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_notes
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
```

4. Lancer l'application :
```bash
./mvnw spring-boot:run
```

L’API sera disponible à l’adresse : `http://localhost:8080`

---

## 🧱 Structure des entités (simplifiée)

### Student
- id
- name
- email

### Course
- id
- title
- description

### Grade
- id
- student_id
- course_id
- score

---

## 📚 Endpoints de l’API

### 🔹 Étudiants
| Méthode | Endpoint              | Description                       |
|--------|------------------------|-----------------------------------|
| POST   | `/students`            | Ajouter un nouvel étudiant        |
| PUT    | `/students/{id}`       | Mettre à jour un étudiant         |
| DELETE | `/students/{id}`       | Supprimer un étudiant             |
| GET    | `/students`            | Récupérer tous les étudiants      |
| GET    | `/students/{id}`       | Récupérer un étudiant par ID      |

### 🔹 Cours
| Méthode | Endpoint              | Description                       |
|--------|------------------------|-----------------------------------|
| POST   | `/courses`             | Ajouter un nouveau cours          |
| PUT    | `/courses/{id}`        | Mettre à jour un cours            |
| DELETE | `/courses/{id}`        | Supprimer un cours                |
| GET    | `/courses`             | Récupérer tous les cours          |
| GET    | `/courses/{id}`        | Récupérer un cours par ID         |

### 🔹 Notes
| Méthode | Endpoint                             | Description                             |
|--------|----------------------------------------|-----------------------------------------|
| POST   | `/grades`                              | Enregistrer une nouvelle note           |
| PUT    | `/grades/{id}`                         | Mettre à jour une note existante        |
| DELETE | `/grades/{id}`                         | Supprimer une note                      |
| GET    | `/grades/student/{studentId}`          | Notes d’un étudiant                     |
| GET    | `/grades/course/{courseId}`            | Notes d’un cours                        |

### 🔹 Rapports
| Méthode | Endpoint                                   | Description                                      |
|--------|---------------------------------------------|--------------------------------------------------|
| GET    | `/reports/course/{courseId}`                | Moyenne des notes pour un cours donné           |
| GET    | `/reports/student/{studentId}`              | Moyenne des notes d’un étudiant                 |

---

## 🧪 Exemple de test avec Postman

### Ajouter un étudiant
```http
POST /students
Content-Type: application/json

{
  "name": "Alice",
  "email": "alice@example.com"
}
```

### Ajouter un cours
```http
POST /courses
Content-Type: application/json

{
  "title": "Mathématiques",
  "description": "Cours de maths niveau L1"
}
```

### Enregistrer une note
```http
POST /grades
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "score": 15.5
}
```

---

## 🙋 Conseils pour les débutants

- Vérifiez que **MySQL est lancé** avant de démarrer l'application.
- Créez toujours un **étudiant et un cours avant d’ajouter une note**.
- Utilisez **Postman** ou Swagger pour tester facilement les requêtes.
- En cas d’erreur 500, consultez la **console Spring Boot** pour lire les logs d’erreur.
- Activez les logs SQL si besoin avec :
```properties
spring.jpa.show-sql=true
```

---

## 📩 Contact

Lien du projet à envoyer à : **paillard.pierre@gmail.com**

Projet réalisé dans le cadre d’un exercice pédagogique.

 
