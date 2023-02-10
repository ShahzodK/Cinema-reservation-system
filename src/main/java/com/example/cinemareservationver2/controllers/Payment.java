package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.swing.*;

public class Payment implements Initializable {
    @FXML
    private Button btnback;

    @FXML
    private Button btnhumo;

    @FXML
    private Button btnms;

    @FXML
    private Button btnuz;

    @FXML
    private Button btnvisa;

    @FXML
    private TextField card1;

    @FXML
    private TextField card2;

    @FXML
    private TextField card3;

    @FXML
    private TextField card4;

    @FXML
    private Button completeCheckout1;

    @FXML
    private Button completeCheckout2;

    @FXML
    private Button completeCheckout3;

    @FXML
    private Button completeCheckout4;

    @FXML
    private TextField cvc3;

    @FXML
    private TextField cvc4;

    @FXML
    private Button exit_button;

    @FXML
    private TextField expiry1;

    @FXML
    private TextField expiry2;

    @FXML
    private TextField expiry3;

    @FXML
    private TextField expiry4;

    @FXML
    private Pane main_pane;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private TextField name3;

    @FXML
    private TextField name4;

    @FXML
    private Pane paneHumo;

    @FXML
    private Pane paneMS;

    @FXML
    private Pane paneUzcard;

    @FXML
    private Pane paneVisa;

    @FXML
    void addAccountCardInfo1(ActionEvent event) throws Exception {
        DatabaseConnection connectInfo1 = new DatabaseConnection();
        Connection connectionInformation1= connectInfo1.getConnection();
        String newAccountCardInfo1="INSERT INTO paymentuz(nameOnCard, cardNumber, expiryDate) VALUES('"+name1.getText()+"',"+card1.getText()+","+expiry1.getText()+")";
        try {
            boolean registervalidate1=!name1.getText().isBlank()&&!card1.getText().isBlank()&&!expiry1.getText().isBlank();
            if(registervalidate1){
                Statement statement1=connectionInformation1.createStatement();
                statement1.executeUpdate(newAccountCardInfo1);
                EmailSender.sendEmail("madiyarovaumida04@gmail.com");
                name1.setText("");
                card1.setText("");
                expiry1.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Please, fill all fields",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void addAccountCardInfo2(ActionEvent event) throws Exception {
        DatabaseConnection connectInfo2 = new DatabaseConnection();
        Connection connectionInformation2= connectInfo2.getConnection();
        String newAccountCardInfo2="INSERT INTO paymentuz(nameOnCard, cardNumber, expiryDate) VALUES('"+name2.getText()+"',"+card2.getText()+","+expiry2.getText()+")";
        try {
            boolean registervalidate2=!name2.getText().isBlank()&&!card2.getText().isBlank()&&!expiry2.getText().isBlank();
            if(registervalidate2){
                Statement statement2=connectionInformation2.createStatement();
                statement2.executeUpdate(newAccountCardInfo2);
                EmailSender.sendEmail("madiyarovaumida04@gmail.com");
                name2.setText("");
                card2.setText("");
                expiry2.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Please, fill all fields",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    void addAccountCardInfo3(ActionEvent event) throws Exception {
        DatabaseConnection connectInfo3 = new DatabaseConnection();
        Connection connectionInformation3= connectInfo3.getConnection();
        String newAccountCardInfo3="INSERT INTO payment(nameOnCard1, cardNumber1, expiryDate1, CvcCvv1) VALUES('"+name3.getText()+"',"+card3.getText()+","+expiry3.getText()+","+cvc3.getText()+")";
        try {
            boolean registervalidate3=!name3.getText().isBlank()&&!card3.getText().isBlank()&&!expiry3.getText().isBlank()&&!cvc3.getText().isBlank();
            if(registervalidate3){
                Statement statement3=connectionInformation3.createStatement();
                statement3.executeUpdate(newAccountCardInfo3);
                EmailSender.sendEmail("madiyarovaumida04@gmail.com");
                name3.setText("");
                card3.setText("");
                expiry3.setText("");
                cvc3.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Please, fill all fields",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    void addAccountCardInfo4(ActionEvent event) throws Exception {
        DatabaseConnection connectInfo4= new DatabaseConnection();
        Connection connectionInformation4= connectInfo4.getConnection();
        String newAccountCardInfo4="INSERT INTO payment(nameOnCard1, cardNumber1, expiryDate1, CvcCvv1) VALUES('"+name4.getText()+"',"+card4.getText()+","+expiry4.getText()+","+cvc4.getText()+")";

        try {
            boolean registervalidate4=!name4.getText().isBlank()&&!card4.getText().isBlank()&&!expiry4.getText().isBlank()&&!cvc4.getText().isBlank();
            if(registervalidate4){
                Statement statement4=connectionInformation4.createStatement();
                statement4.executeUpdate(newAccountCardInfo4);
                EmailSender.sendEmail("madiyarovaumida04@gmail.com");
                name4.setText("");
                card4.setText("");
                expiry4.setText("");
                cvc4.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Please, fill all fields",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }

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
