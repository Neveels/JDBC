package com.example.jdbc;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableColumn<?, ?> column_capitalName;

    @FXML
    private TableColumn<?, ?> column_capitalPopulation;

    @FXML
    private TableColumn<?, ?> column_capitalSquare;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<?, ?> column_name;

    @FXML
    private TableColumn<?, ?> column_population;

    @FXML
    private TableColumn<?, ?> column_square;

    @FXML
    private TextField field_capitalName;

    @FXML
    private TextField field_capitalPopulation;

    @FXML
    private TextField field_capitalSquare;

    @FXML
    private TextField field_name;

    @FXML
    private TextField field_population;

    @FXML
    private TextField field_square;

    @FXML
    private TableView<?> table;

    @FXML
    void initialize() {

    }
}
