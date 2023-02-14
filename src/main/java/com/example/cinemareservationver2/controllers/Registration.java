package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.Encryptor;
import com.example.cinemareservationver2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;

public class Registration {
    Encryptor encrypt = new Encryptor();
    @FXML
    private PasswordField conpasswordText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField firstnameText;

    @FXML
    private TextField lastnameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField phonenumberText;

    @FXML
    private TextField usernameText;

    @FXML
    private Label messageLabel;

    @FXML
    void gotologin(ActionEvent event) throws IOException {
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
    void exit(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
    ////////////////////////
    public void signupButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        Boolean registervalidate=!usernameText.getText().isBlank()&&!firstnameText.getText().isBlank()&&!lastnameText.getText().isBlank()&&!passwordText.getText().isBlank()&&!conpasswordText.getText().isBlank()&&!phonenumberText.getText().isBlank()&&!emailText.getText().isBlank();


        if(registervalidate){
            if(conpasswordText.getText().equals(passwordText.getText())){
                addAccount();
            }
            else{
                messageLabel.setText("Password and Confirm password should be same");
            }
        }
        else{
            messageLabel.setText("Please fill all of above");
        }

    }
    public void addAccount() throws NoSuchAlgorithmException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String newAccount="INSERT INTO users(firstname, lastname, username, password, email, phonenumber) VALUES('"+firstnameText.getText()+"', '"+lastnameText.getText()+"', '"+usernameText.getText()+"', '"+passwordText.getText() +"', '"+emailText.getText()+"', '"+phonenumberText.getText()+"')";
        try {
            Statement statement=connectionDB.createStatement();
            statement.executeUpdate(newAccount);
            messageLabel.setText("Registration has been successful Know sign in with this account and enjoy");
            firstnameText.setText("");
            lastnameText.setText("");
            usernameText.setText("");
            emailText.setText("");
            passwordText.setText("");
            conpasswordText.setText("");
            phonenumberText.setText("");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
