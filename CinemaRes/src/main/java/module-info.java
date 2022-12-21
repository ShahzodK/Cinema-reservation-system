module com.example.cinemares {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.cinemares to javafx.fxml;
    exports com.example.cinemares;
}