package com.example.cinemares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Registration {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }
    @FXML
    private TextField confirmpaswordText;

    @FXML
    private TextField firstnameText;

    @FXML
    private TextField gmailText;

    @FXML
    private TextField lastnameText;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField phonenumberText;

    @FXML
    private TextField usernameText;
    @FXML
    private Button signupButton;
    @FXML
    private Label messageLabel;


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        Parent homePage= FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Scene homepagescene=new Scene(homePage);
        Stage appstage=(Stage) ((Node)event.getSource()).getScene().getWindow();

        appstage.setScene(homepagescene);
        appstage.show();
    }
    public void signupButtonOnAction(ActionEvent event) throws IOException{
        Boolean registervalidate=!usernameText.getText().isBlank()&&!firstnameText.getText().isBlank()&&!lastnameText.getText().isBlank()&&!passwordText.getText().isBlank()&&!confirmpaswordText.getText().isBlank()&&!phonenumberText.getText().isBlank()&&!gmailText.getText().isBlank();


        if(registervalidate){
            if(confirmpaswordText.getText().equals(passwordText.getText())){
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
    public void addAccount(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String newAccount="INSERT INTO users(firstname, lastname, username, password, gmail, phonenumber) VALUES('"+firstnameText.getText()+"', '"+lastnameText.getText()+"', '"+usernameText.getText()+"', '"+passwordText.getText()+"', '"+gmailText.getText()+"', '"+phonenumberText.getText()+"')";
        try {
            Statement statement=connectionDB.createStatement();
            statement.executeUpdate(newAccount);
            messageLabel.setText("Registration has been successful Know sign in with this account and enjoy");
            firstnameText.setText("");
            lastnameText.setText("");
            usernameText.setText("");
            gmailText.setText("");
            passwordText.setText("");
            confirmpaswordText.setText("");
            phonenumberText.setText("");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
