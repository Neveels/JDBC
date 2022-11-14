package com.example.jdbc.entity;

import javafx.beans.property.*;

public class StudentProperty {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty age;

    private final SimpleStringProperty university;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty email;

    public StudentProperty(Student student) {
        id = new SimpleIntegerProperty(student.getId());
        firstName = new SimpleStringProperty(student.getFirstName());
        secondName = new SimpleStringProperty(student.getSecondName());
        university = new SimpleStringProperty(student.getUniversity());
        phoneNumber = new SimpleStringProperty(student.getPhoneNumber());
        email = new SimpleStringProperty(student.getEmail());
        age = new SimpleIntegerProperty(student.getAge());
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getUniversity() {
        return university.get();
    }

    public void setUniversity(String university) {
        this.university.set(university);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
