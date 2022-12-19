module com.example.cinemares {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cinemares to javafx.fxml;
    exports com.example.cinemares;
}