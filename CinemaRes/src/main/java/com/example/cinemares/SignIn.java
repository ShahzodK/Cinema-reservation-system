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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.ResourceBundle;

public class SignIn {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }
    @FXML
    private TextField passwordText;
    @FXML
    private Label signinLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Button signinButton;

    @FXML
    private TextField usernameText;
    private Boolean signinBool=false;

    public void registerButtonOnAction(ActionEvent event) throws IOException{

        Parent homePage= FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene homepagescene=new Scene(homePage);
        Stage appstage=(Stage) ((Node)event.getSource()).getScene().getWindow();

        appstage.setScene(homepagescene);
        appstage.show();
    }
    public void loginButtonOnAction(ActionEvent event)throws IOException{
        if(usernameText.getText().isBlank()==false&&passwordText.getText().isBlank()==false){
            validateLogin();
            if(signinBool){
                Parent homePage= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene homepagescene=new Scene(homePage);
                Stage appstage=(Stage) ((Node)event.getSource()).getScene().getWindow();

                appstage.setScene(homepagescene);
                appstage.show();
            }
        }
        else {
            signinLabel.setText("Please enter username and password");
        }

    }
    public void validateLogin(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String verifyLogin="SELECT count(1) FROM users WHERE username = '"+usernameText.getText()+"' AND password ='"+passwordText.getText()+"'";
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


}
