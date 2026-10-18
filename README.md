# Student Management System

A console-based Student Management System built using Java, MySQL, JDBC, and Maven. The application enables users to manage student records through a simple menu-driven interface.

## Features

- **Add Student:** Register a student with their name, email, course, and age.
- **View Students:** Retrieve and display student records from MySQL.
- **Update Student:** Modify existing student information using the student ID.
- **Delete Student:** Remove a student record after confirmation.
- **Input Validation:** Validate required fields and user input.
- **Database Integration:** Use JDBC and prepared statements to interact with MySQL.
- **Environment Configuration:** Store database credentials in a local `.env` file.

## Tech Stack

- Java
- MySQL 8
- JDBC
- Maven
- MySQL Connector/J
- dotenv-java
- Git and GitHub

## Project Structure

```text
SMSJava/
├── src/
│   ├── main/
│   │   └── java/com/shivam/sms/
│   │       ├── Main.java
│   │       ├── App.java
│   │       ├── Student.java
│   │       ├── dao/
│   │       │   └── StudentDAO.java
│   │       └── util/
│   │           └── DBConnection.java
│   └── test/
├── .env.example
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

Install the following before running the application:

- JDK
- Apache Maven
- MySQL Server

## Database Setup

1. Open MySQL Workbench or the MySQL command-line client.
2. Create the database:

```sql
CREATE DATABASE sms_db;
USE sms_db;
```

3. Create the students table:

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL,
    age INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Environment Configuration

Create a `.env` file in the project root, alongside `pom.xml`.

Add your local database settings:

```dotenv
DB_URL=jdbc:mysql://localhost:3306/sms_db
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

Replace the password placeholder with your own MySQL password. Never commit your real `.env` file to GitHub.

## Build and Run

Open a terminal in the project root.

Compile the application:

```bash
mvn clean compile
```

Run `Main.java` through VS Code, or use the appropriate Java run configuration.

The application presents a menu for adding, viewing, updating, and deleting student records.

## Security and Reliability

- Database credentials are configured outside the source code.
- Prepared statements help prevent SQL injection.
- Database operations use JDBC.
- The `.gitignore` file excludes local secrets and Maven build output.

## Future Improvements

- Search students by ID, name, or course.
- Add attendance and marks management.
- Introduce service-layer separation.
- Add automated unit and integration tests.
- Add a graphical user interface.

## Author

Shivam Kumar

B.Tech Computer Science and Engineering
