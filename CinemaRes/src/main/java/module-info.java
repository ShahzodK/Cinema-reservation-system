module com.example.cinemares {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;


    opens com.example.cinemares to javafx.fxml;
    exports com.example.cinemares;
}