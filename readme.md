# 📌 ToDo List Application


A simple ToDo List application built with Spring Boot, providing task management features.

## 🚀 Features
✔️ Add, edit, and delete tasks  
✔️ Assign tasks to users  
✔️ Mark tasks as completed  
✔️ User authentication and authorization  
✔️ Database migration with Flyway

## 🛠 Technologies Used
| Technology       | Purpose                     |
|------------------|-----------------------------|
| **Spring Boot**  | Backend framework           |
| **Spring Data JPA** | Database management         |
| **Spring Security** | Authentication & authorization |
| **Thymeleaf**    | Templating engine            |
| **PostgreSQL**   | Database                    |
| **Flyway**       | Database migrations         |
| **MapStruct**    | DTO mapping                 |
| **Testcontainers** | Integration testing          |

## 📥 Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/todo-app.git
   cd todo-app

## 🔌Database
1. Configure the database connection in application.properties:
   ```sh
   spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword

2. Run database migrations:
   ```sh
   mvn flyway:migrate 

## ▶️ Running the Application

1. To start the application, use:
   ```sh
   mvn spring-boot:run

The app should be accessible at: http://localhost:8080

## 🧪 Testing
1. To run tests:

   ```sh 
   mvn test
   

## 📜 License
This project is licensed under the MIT License - see the [License](https://github.com/krawczykMichal/ToDoList/blob/master/MitLicense) file for details.

## 👤 Author
Created by Michał Krawczyk. Feel free to contribute or report issues!

