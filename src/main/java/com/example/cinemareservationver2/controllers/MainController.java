package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private BorderPane borderpane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }
    @FXML
    private Label welcomeText;
    @FXML
    private Button logout;

    @FXML
    public void logoutAction(ActionEvent event) throws IOException {

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
    void browse(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/browse.fxml"));
        borderpane.setCenter(fxml);

    }

    @FXML
    void search(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/search.fxml"));
        borderpane.setCenter(fxml);
    }
    @FXML
    void account(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/account.fxml"));
        borderpane.setCenter(fxml);
    }
    @FXML
    void history(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/history.fxml"));
        borderpane.setCenter(fxml);
    }
    @FXML
    void faq(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/faq.fxml"));
        borderpane.setCenter(fxml);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}