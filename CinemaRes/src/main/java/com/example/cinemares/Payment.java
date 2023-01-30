package com.example.cinemares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Payment implements Initializable {
    @FXML
    private Button btnback;

    @FXML
    private Button exit_button;

    @FXML
    private Button btnhumo;

    @FXML
    private Button btnms;

    @FXML
    private Button btnuz;

    @FXML
    private Button btnvisa;

    @FXML
    private Button completeCheckout;

    @FXML
    private Pane main_pane;

    @FXML
    private Pane paneHumo;

    @FXML
    private Pane paneMS;

    @FXML
    private Pane paneUzcard;

    @FXML
    private Pane paneVisa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    Stage stage;
    @FXML
    public void handleClicks(ActionEvent event)
    {

        if (event.getSource() == btnuz)
        {
            paneUzcard.toFront();
        }
        else if (event.getSource() == btnhumo)
        {
            paneHumo.toFront();
        }
        else if (event.getSource() == btnvisa)
        {
            paneVisa.toFront();
        }
        else if (event.getSource() == btnms)
        {
            paneMS.toFront();
        }
        else if (event.getSource() == btnback)
        {
            main_pane.toFront();
        }
        else if (event.getSource() == exit_button)
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
