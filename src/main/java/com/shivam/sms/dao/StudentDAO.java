package com.shivam.sms.dao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.shivam.sms.Student;
import com.shivam.sms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students (name, email, course, age) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getAge());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    
public List<Student> getAllStudents() {

    List<Student> students = new ArrayList<>();

    String sql = "SELECT id, name, email, course, age FROM students";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()
    ) {
        while (resultSet.next()) {

            Student student = new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("course"),
                resultSet.getInt("age")
            );

            students.add(student);
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving students: " + e.getMessage());
    }

    return students;
}

public boolean updateStudent(int id, String name, String email,
                             String course, int age) {

    String sql = "UPDATE students SET name = ?, email = ?, course = ?, age = ? WHERE id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, course);
        statement.setInt(4, age);
        statement.setInt(5, id);

        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;

    } catch (SQLException e) {
        System.out.println("Error updating student: " + e.getMessage());
        return false;
    }
}

public boolean deleteStudent(int id) {

    String sql = "DELETE FROM students WHERE id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();

        return rowsDeleted > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting student: " + e.getMessage());
        return false;
    }
}


}
