module com.example.jdbc {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example.jdbc.entity to javafx.fxml, javafx.base;
    opens com.example.jdbc.command to javafx.fxml;
    opens com.example.jdbc.server to javafx.fxml;
    opens com.example.jdbc to javafx.fxml;

    exports com.example.jdbc.entity to javafx.fxml;
    exports com.example.jdbc.command to javafx.fxml;
    exports com.example.jdbc.server to javafx.fxml;
    exports com.example.jdbc;
}