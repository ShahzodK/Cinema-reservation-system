package com.example.cinemares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Payment implements Initializable {
    @FXML
    private Button btnHumo;

    @FXML
    private Button btnMC;

    @FXML
    private Button btnUzcard;

    @FXML
    private Button btnVisa;
//
//    @FXML
//    private ImageView photoStatus;

    @FXML
    private GridPane pnHumo;

    @FXML
    private GridPane pnMS;

    @FXML
    private GridPane pnUzcard;

    @FXML
    private GridPane pnVisa;

    @FXML
    private Pane imgHumo;

    @FXML
    private Pane imgMC;

    @FXML
    private Pane imgUzcard;

    @FXML
    private Pane imgVisa;

    @FXML
    private Button completeCheckout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event)
    {

        if (event.getSource() == btnUzcard)
        {
            pnUzcard.toFront();
            imgUzcard.toFront();
        }
        else if (event.getSource() == btnHumo)
        {
            pnHumo.toFront();
            imgHumo.toFront();
        }
        else if (event.getSource() == btnVisa)
        {
            pnVisa.toFront();
            imgVisa.toFront();
        }
        else if (event.getSource() == btnMC)
        {
            pnMS.toFront();
            imgMC.toFront();
        }
    }
}
