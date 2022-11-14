package com.example.jdbc.server.repo.impl;

import com.example.jdbc.entity.Student;
import com.example.jdbc.server.db.DataBaseConnection;
import com.example.jdbc.server.repo.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    Connection connection = DataBaseConnection.getConnection();

    @Override
    public List<Student> gelAll() {
        List<Student> students;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM student";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            students = new ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setUniversity(resultSet.getString("university"));
                student.setEmail(resultSet.getString("email"));
                student.setFirstName(resultSet.getString("name"));
                student.setSecondName(resultSet.getString("surname"));
                student.setPhoneNumber(resultSet.getString("phoneNumber"));

                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    @Override
    public void deleteById(Integer id) {
        String sql  = "DELETE FROM student " +
                "WHERE id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Student student) {

        String sqlRequest = "INSERT INTO student (age, university, name, surname, email, phoneNumber) " +
                "VALUES(?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, student.getAge());
            statement.setString(2, student.getUniversity());
            statement.setString(3, student.getFirstName());
            statement.setString(4, student.getSecondName());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET" +
                " age = ?," +
                " university = ?," +
                " name = ?," +
                " surname = ?," +
                " email = ?," +
                " phoneNumber = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getAge());
            preparedStatement.setString(2, student.getUniversity());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getSecondName());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPhoneNumber());
            preparedStatement.setInt(7, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
