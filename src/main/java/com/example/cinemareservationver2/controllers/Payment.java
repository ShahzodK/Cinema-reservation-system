package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Payment implements Initializable {
    @FXML
    private Button btnback, btnhumo, btnms, btnuz, btnvisa, completeCheckout, exit_button;
    @FXML
    private Pane main_pane, paneOrderDetails;
    @FXML
    private TextField cardHolderName, cardNumber, cardExpDate, cardVerCode;
    @FXML
    private ImageView cardIcon;
    @FXML
    private Label orderid, moviename, showcasedate, purchaser, ticketprice, ticketquantity, totalprice;

    //TODO get variables from Login and Movie classes
    private String username = "bonniecoder";
    private int movieid = 5;
    private int numberOfTickets = 2;

    private String firstName, lastName, paymentType, cardFirstDigit, movie;
    private int total, ticketNumber;
    private boolean paymentInfoValid, bool;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    public void handleClicks(ActionEvent event) throws Exception {
        if (event.getSource() == btnuz) {
            paneOrderDetails.toFront();
            showOrderDetails();
            paymentType = "uzcard";
            cardVerCode.setVisible(false);
            cardIcon.setVisible(false);
        } else if (event.getSource() == btnhumo) {
            paneOrderDetails.toFront();
            showOrderDetails();
            paymentType = "humo";
            cardVerCode.setVisible(false);
            cardIcon.setVisible(false);
        } else if (event.getSource() == btnvisa) {
            paneOrderDetails.toFront();
            showOrderDetails();
            paymentType = "visa";
            cardVerCode.setVisible(true);
            cardIcon.setVisible(true);
        } else if (event.getSource() == btnms) {
            paneOrderDetails.toFront();
            showOrderDetails();
            paymentType = "mastercard";
            cardVerCode.setVisible(true);
            cardIcon.setVisible(true);
        } else if (event.getSource() == btnback) {
            main_pane.toFront();
            cardHolderName.clear();
            cardNumber.clear();
            cardExpDate.clear();
            cardVerCode.clear();
        } else if (event.getSource() == exit_button) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else if (event.getSource() == completeCheckout) {
            validate();
        }
    }

    private void showOrderDetails() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String getOrders = "SELECT * FROM orders WHERE id=(SELECT max(id) FROM orders)";
        String getMovies = "SELECT * FROM movies WHERE id = " + movieid + "";
        String getPurchaser = "SELECT * FROM users WHERE username = '" + username + "'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet orderNumber = statement.executeQuery(getOrders);
            while (orderNumber.next()) {
                ticketNumber = orderNumber.getInt("id") + 1;
                orderid.setText(String.valueOf(ticketNumber));
            }
            ResultSet movies = statement.executeQuery(getMovies);
            while (movies.next()) {
                movie = movies.getString("name");
                ticketprice.setText(movies.getString("price"));
                total = movies.getInt("price") * numberOfTickets;
                totalprice.setText(String.valueOf(total));
            }
            ResultSet purchaserName = statement.executeQuery(getPurchaser);
            while (purchaserName.next()) {
                firstName = purchaserName.getString("firstname");
                lastName = purchaserName.getString("lastname");
            }
            purchaser.setText(firstName + " " + lastName);
            moviename.setText(movie);
            showcasedate.setText("10/02/2023");
            ticketquantity.setText(String.valueOf(numberOfTickets));
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void addOrder() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String newOrder = "INSERT INTO orders(username, moviename,ticketnumber, totalprice) VALUES ('" + username + "','" + movie + "'," + ticketNumber + "," + total + ")";
        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(newOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validate() throws Exception {
        if (paymentType.equals("uzcard") || paymentType.equals("humo")) {
            paymentInfoValid = !cardHolderName.getText().isBlank() && !cardNumber.getText().isBlank() && !cardExpDate.getText().isBlank();
        } else if (paymentType.equals("visa") || paymentType.equals("mastercard")) {
            paymentInfoValid = !cardHolderName.getText().isBlank() && !cardNumber.getText().isBlank() && !cardExpDate.getText().isBlank() && !cardVerCode.getText().isBlank();
        }
        //TODO set input length limit
        if (paymentInfoValid) {
            if (validCardNumber()) {
                if (checkForExistance()) {
                    //Success message
                    JOptionPane.showMessageDialog(null,
                            "You successfully booked the ticket!",
                            "Success",
                            JOptionPane.ERROR_MESSAGE);
                    addOrder();
                    main_pane.toFront();
                    cardHolderName.clear();
                    cardNumber.clear();
                    cardExpDate.clear();
                    cardVerCode.clear();
                    EmailSender.sendEmail("bonniemathers99@gmail.com");
                } else {
                    //Error message
                    JOptionPane.showMessageDialog(null,
                            "Invalid card number. The card number you entered does not exist",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                //Error message
                JOptionPane.showMessageDialog(null,
                        "Invalid card number. Card number must start with "+cardFirstDigit,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            //Error message
            JOptionPane.showMessageDialog(null,
                    "Please, fill all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean validCardNumber() {
        boolean bool;
        if(paymentType.equals("uzcard")) {cardFirstDigit="8";}
        else if(paymentType.equals("humo")) {cardFirstDigit="9";}
        else if(paymentType.equals("visa")) {cardFirstDigit="4";}
        else if(paymentType.equals("mastercard")) {cardFirstDigit="5";}
        if (cardNumber.getText().startsWith(cardFirstDigit)) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    String cardHolder, cardExp, cardCvc;
    private boolean checkForExistance() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String cardInfo = "SELECT * FROM cards WHERE number = '" + cardNumber.getText() + "'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet result = statement.executeQuery(cardInfo);
            while (result.next()) {
                cardHolder = result.getString("holder_name");
                cardExp = result.getString("exp_date");
                cardCvc = result.getString("cvc");
            }
            if (paymentType.equals("uzcard") || paymentType.equals("humo")) {
                if (cardHolder.equalsIgnoreCase(cardHolderName.getText())&&cardExp.equals(cardExpDate.getText())) {
                    bool = true;
                } else {
                    bool = false;
                }
            } else if (paymentType.equals("visa") || paymentType.equals("mastercard")) {
                if (cardHolder.equals(cardHolderName.getText())&&cardExp.equals(cardExpDate.getText())&&cardCvc.equals(cardVerCode.getText())) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }
}