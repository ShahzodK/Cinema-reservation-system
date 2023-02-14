package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.Encryptor;
import com.example.cinemareservationver2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    public static String username ;

    Encryptor encrypt = new Encryptor();

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label signinLabel;

    @FXML
    private TextField usernameText;
    private Boolean signinBool=false;


    @FXML
    void gotoregistration(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/registration.fxml"));
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
    /////
    public void loginButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if(usernameText.getText().isBlank()==false&&passwordText.getText().isBlank()==false){
            validateLogin();
            if(signinBool){
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                primaryStage.initStyle(StageStyle.DECORATED);
                primaryStage.setFullScreen(true);
                primaryStage.setScene(scene);
                primaryStage.show();
                username = usernameText.getText();
                System.out.println(username);
            }
        }
        else {
            signinLabel.setText("Please enter username and password");
        }

    }
    public void validateLogin() throws NoSuchAlgorithmException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String verifyLogin="SELECT count(1) FROM users WHERE username = '"+usernameText.getText()+"' AND password ='" + passwordText.getText() +"'";
        try{
            Statement statement=connectionDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if (queryResult.getInt(1)==1){

                    signinBool=true;
                }
                else{
                    signinLabel.setText("Invalid login. Please try again.");
                    signinBool=false;
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }
    public String getUsername(){
        return username;
    }
}
