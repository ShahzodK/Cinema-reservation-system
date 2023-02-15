package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class History implements Initializable {

    @FXML
    private TableColumn<Order, Integer> numberOfTickets;
    @FXML
    private TableColumn<Order, Integer> id;
    @FXML
    private TableColumn<Order, String> movieName;
    @FXML
    private TableColumn<Order, Integer> price;
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, Integer> ticketNumber;
    @FXML
    private TableColumn<Order, String> time;

    private static final ObservableList<Order> data = FXCollections.observableArrayList();
    String movName, usName, orderTime;
    int movieid, moviePrice, usid, tickNum, tickQuan;
    public void historygenerator(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String query = "SELECT * FROM orders WHERE userid = "+3+";";
        try {

            for(int i=1;i<10;i++) {
                Statement statement = connectionDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 1);
                ResultSet orders = statement.executeQuery(query);
                orders.absolute(i);
                movieid = orders.getInt("id");
                usid = orders.getInt("userid");
                orderTime = String.valueOf(orders.getDate("date"));
                tickQuan = orders.getInt("ticketnumber");
                tickNum = orders.getInt("id");
                String getMovies = "SELECT * FROM movies WHERE id = " + String.valueOf(movieid) + ";";
                ResultSet movies = statement.executeQuery(getMovies);
                while(movies.next()) {
                    movName = movies.getString("name");
                    moviePrice = movies.getInt("price");}
                String getUsers = "SELECT * FROM users WHERE id = " + String.valueOf(usid) + ";";
                ResultSet users = statement.executeQuery(getUsers);
                while(users.next()) {
                    usName = users.getString("firstname")+users.getString("lastname");}
                Order order = new Order(i, movName, orderTime, tickNum, tickQuan, moviePrice );
                data.add(order);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        ticketNumber.setCellValueFactory(new PropertyValueFactory<>("ticketNumber"));
        numberOfTickets.setCellValueFactory(new PropertyValueFactory<>("numberOfTickets"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        historygenerator();
        table.setItems(data);
    }
}
