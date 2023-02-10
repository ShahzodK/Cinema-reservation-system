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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Account implements Initializable {
    Login username = new Login();
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

    }

    @FXML
    void saveFirstName(ActionEvent event) {

    }

    @FXML
    void saveLastName(ActionEvent event) {

    }

    @FXML
    void savePassword(ActionEvent event) {

    }

    @FXML
    void savePhone(ActionEvent event) {
        System.out.println(username.getUsername());
    }

    @FXML
    void saveUsername(ActionEvent event) {
        Boolean username_validate = !newUsername.getText().isBlank();
        if (username_validate){
            changeUsername();
        }
    }
    public void changeUsername(){
        Login username = new Login();
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String newAccount="UPDATE users SET username = '"+ newUsername.getText() +"' WHERE username = '"+  "';";
        try {
            Statement statement=connectionDB.createStatement();
            statement.executeUpdate(newAccount);
            newUsername.setText("");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
