module com.example.cinemareservationver2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;


    opens com.example.cinemareservationver2 to javafx.fxml;
    exports com.example.cinemareservationver2;
    exports com.example.cinemareservationver2.controllers;
    opens com.example.cinemareservationver2.controllers to javafx.fxml;
}