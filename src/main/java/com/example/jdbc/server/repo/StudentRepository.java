package com.example.jdbc.server.repo;

import com.example.jdbc.entity.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> gelAll();

    void deleteById(Integer id);

    void save(Student student);

    void update(Student student);

}
