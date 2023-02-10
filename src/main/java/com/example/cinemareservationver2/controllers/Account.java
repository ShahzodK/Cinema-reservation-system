package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Account implements Initializable {

    String username = Login.username;

    @FXML
    private TextField newEmail;

    @FXML
    private TextField newFirstName;

    @FXML
    private TextField newLastName;

    @FXML
    private TextField newPassConf;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPhone;

    @FXML
    private TextField newUsername;

    @FXML
    void logout(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void saveEmail(ActionEvent event) {
        save(newEmail, "email");
    }

    @FXML
    void saveFirstName(ActionEvent event) {
        save(newFirstName, "firstname");
    }

    @FXML
    void saveLastName(ActionEvent event) {
        save(newLastName, "lastname");
    }

    @FXML
    void savePassword(ActionEvent event) {
        save(newPassword, "password");
    }

    @FXML
    void savePhone(ActionEvent event) {
        save(newPhone, "phonenumber");
    }

    @FXML
    void saveUsername(ActionEvent event) {
        save(newUsername, "username");
    }

    void save(TextField field, String column) {
        boolean validate = !field.getText().isBlank();
        if(validate) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectionDB = connectNow.getConnection();
            String update = "UPDATE users SET " + column + "='" + field.getText() + "' WHERE username='" + username + "'";
            try {
                Statement statement = connectionDB.createStatement();
                statement.executeUpdate(update);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TODO add success message
            field.clear();
        } else { error(); }
    }

    void error() {
        JOptionPane.showMessageDialog(null,
                "Please, fill the field.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
