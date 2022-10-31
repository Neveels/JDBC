package com.example.jdbc.server.repo.impl;

import com.example.jdbc.entity.AddressBook;
import com.example.jdbc.server.db.DataBaseConnection;
import com.example.jdbc.server.repo.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    Connection connection = DataBaseConnection.getConnection();

    @Override
    public List<AddressBook> gelAll() {
        List<AddressBook> addressBooks;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM AddressBook";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            addressBooks = new ArrayList<>();

            while (resultSet.next()) {
                AddressBook addressBook = new AddressBook();
                addressBook.setId(resultSet.getInt("id"));
                addressBook.setAge(resultSet.getInt("age"));
                addressBook.setCountry(resultSet.getString("country"));
                addressBook.setEmail(resultSet.getString("email"));
                addressBook.setFirstName(resultSet.getString("name"));
                addressBook.setSecondName(resultSet.getString("surname"));
                addressBook.setPhoneNumber(resultSet.getString("phoneNumber"));

                addressBooks.add(addressBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addressBooks;
    }

    @Override
    public void deleteById(Integer id) {
        String sql  = "DELETE FROM AddressBook " +
                "WHERE id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(AddressBook addressBook) {

        String sqlRequest = "INSERT INTO AddressBook (age, country, name, surname, email, phoneNumber) " +
                "VALUES(?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, addressBook.getAge());
            statement.setString(2, addressBook.getCountry());
            statement.setString(3, addressBook.getFirstName());
            statement.setString(4, addressBook.getSecondName());
            statement.setString(5, addressBook.getEmail());
            statement.setString(6, addressBook.getPhoneNumber());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(AddressBook addressBook) {
        String sql = "UPDATE AddressBook SET" +
                " age = ?," +
                " country = ?," +
                " name = ?," +
                " surname = ?," +
                " email = ?," +
                " phoneNumber = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,addressBook.getAge());
            preparedStatement.setString(2, addressBook.getCountry());
            preparedStatement.setString(3, addressBook.getFirstName());
            preparedStatement.setString(4, addressBook.getSecondName());
            preparedStatement.setString(5, addressBook.getEmail());
            preparedStatement.setString(6, addressBook.getPhoneNumber());
            preparedStatement.setInt(7, addressBook.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
