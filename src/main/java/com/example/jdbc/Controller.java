package com.example.jdbc;


import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.example.jdbc.command.Command;
import com.example.jdbc.entity.Student;
import com.example.jdbc.entity.StudentProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    private final ObservableList<StudentProperty> tableAddressBookProperties = FXCollections.observableArrayList();

    private ConnectionTCP connectionTCP;

    @FXML
    private Button button_add;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_exit;

    @FXML
    private Button button_read;

    @FXML
    private Button button_update;

    @FXML
    private TableColumn<?, ?> column_age;

    @FXML
    private TableColumn<?, ?> column_university;

    @FXML
    private TableColumn<?, ?> column_email;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<Student, Integer> column_name;

    @FXML
    private TableColumn<?, ?> column_phonenumber;

    @FXML
    private TableColumn<?, ?> column_surname;

    @FXML
    private TextField field_age;

    @FXML
    private TextField field_university;

    @FXML
    private TextField field_email;

    @FXML
    private TextField field_name;

    @FXML
    private TextField field_phoneNumber;

    @FXML
    private TextField field_surname;

    @FXML
    private TableView<StudentProperty> table;

    @FXML
    void initialize() {

        try {
            connectionTCP = new ConnectionTCP(new Socket("127.0.0.1", 8000));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column_surname.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        column_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        column_university.setCellValueFactory(new PropertyValueFactory<>("university"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        button_add.setOnAction(actionEvent -> {
            Student student = new Student();
            student.setId(10);
            student.setFirstName(field_name.getText().trim());
            student.setSecondName(field_surname.getText().trim());
            student.setPhoneNumber(field_phoneNumber.getText().trim());
            student.setAge(Integer.valueOf(field_age.getText().trim()));
            student.setUniversity(field_university.getText().trim());
            student.setEmail(field_email.getText().trim());

            connectionTCP.writeObject(Command.CREATE);
            connectionTCP.writeObject(student);
        });

        button_read.setOnAction(actionEvent -> {
            tableAddressBookProperties.clear();// чтобы не добавлять каждый раз к существующему списку
            connectionTCP.writeObject(Command.READ);
            List<Student> countries = (List<Student>) connectionTCP.readObject();
            for (int i = 0; i < countries.size(); i++) {
                StudentProperty e = new StudentProperty(countries.get(i));
                tableAddressBookProperties.add(e);
            }
            table.setItems(tableAddressBookProperties);
        });

        button_update.setOnAction(actionEvent -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                field_name.setText(table.getSelectionModel().getSelectedItem().getFirstName());
                field_surname.setText(table.getSelectionModel().getSelectedItem().getSecondName());
                field_university.setText(table.getSelectionModel().getSelectedItem().getUniversity());
                field_age.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getAge()));
                field_phoneNumber.setText(table.getSelectionModel().getSelectedItem().getPhoneNumber());
                field_email.setText(table.getSelectionModel().getSelectedItem().getEmail());
            } else {
                field_name.setText("Choose any item");
            }
            button_update.setOnAction(e -> {
                Student addressBook = new Student();
                addressBook.setId(table.getSelectionModel().getSelectedItem().getId());
                addressBook.setEmail(field_email.getText().trim());
                addressBook.setFirstName(field_name.getText().trim());
                addressBook.setSecondName(field_surname.getText().trim());
                addressBook.setAge(Integer.valueOf(field_age.getText().trim()));
                addressBook.setUniversity(field_university.getText().trim());
                addressBook.setPhoneNumber(field_phoneNumber.getText().trim());

                connectionTCP.writeObject(Command.UPDATE);
                connectionTCP.writeObject(addressBook);
            });
        });

        button_delete.setOnAction(actionEvent -> {
            Integer id = table.getSelectionModel().getSelectedItem().getId();
            connectionTCP.writeObject(Command.DELETE);
            connectionTCP.writeObject(id);
        });

        button_exit.setOnAction(actionEvent -> {
            connectionTCP.writeObject(Command.EXIT);
            System.exit(0);
        });

    }
}
