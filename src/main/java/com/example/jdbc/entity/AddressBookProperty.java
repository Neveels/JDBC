package com.example.jdbc.entity;

import javafx.beans.property.*;

public class AddressBookProperty {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty age;

    private final SimpleStringProperty country;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty email;

    public AddressBookProperty(AddressBook addressBook) {
        id = new SimpleIntegerProperty(addressBook.getId());
        firstName = new SimpleStringProperty(addressBook.getFirstName());
        secondName = new SimpleStringProperty(addressBook.getSecondName());
        country = new SimpleStringProperty(addressBook.getCountry());
        phoneNumber = new SimpleStringProperty(addressBook.getPhoneNumber());
        email = new SimpleStringProperty(addressBook.getEmail());
        age = new SimpleIntegerProperty(addressBook.getAge());
    }
//
//    public AddressBook toAddressBook() {
//        return new AddressBook(id.intValue(),
//                age.intValue(),
//                country.getValue(),
//                firstName.getValue(),
//                secondName.getValue(),
//                phoneNumber.getValue(),
//                email.getValue());
//
//    }

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

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
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
