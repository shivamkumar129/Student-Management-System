
package com.shivam.sms;

import com.shivam.sms.dao.StudentDAO;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            int choice = readInteger("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    System.out.println("Thank you for using the application!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Select 1 to 5.");
            }

        }
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");

        String name = readRequiredText("Enter name: ");
        String email = readEmail();
        String course = readRequiredText("Enter course: ");
        int age = readInteger("Enter age (1-120): ", 1, 120);

        Student student = new Student(name, email, course, age);

        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Could not add student. Check the email and database error.");
        }
    }

    private static void viewStudents() {
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        for (Student student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Age: " + student.getAge());
            System.out.println("---------------------------");
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");

        int id = readInteger("Enter student ID to update: ", 1, Integer.MAX_VALUE);
        String name = readRequiredText("Enter new name: ");
        String email = readEmail();
        String course = readRequiredText("Enter new course: ");
        int age = readInteger("Enter new age (1-120): ", 1, 120);

        if (studentDAO.updateStudent(id, name, email, course, age)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found or update failed.");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");

        int id = readInteger("Enter student ID to delete: ", 1, Integer.MAX_VALUE);

        System.out.print("Are you sure you want to delete student ID "
                + id + "? (yes/no): ");

        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (studentDAO.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found or deletion failed.");
        }
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty. Try again.");
        }
    }

    private static String readEmail() {
        while (true) {
            String email = readRequiredText("Enter email: ");

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Please enter a valid email address.");
        }
    }

    private static int readInteger(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
