package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.Browse;
import com.example.cinemareservationver2.DatabaseConnection;
import com.example.cinemareservationver2.FilmPage;
import com.example.cinemareservationver2.HelloApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    public static void setChoosennam(String choosennam) {
        Browse.choosennam = choosennam;
    }
    @FXML
    private AnchorPane filmload;
    @FXML
    private TextField inputSearch;

    @FXML
    private GridPane gridPaneLayout;

    @FXML
    void search(javafx.event.ActionEvent event) {
        gridPaneLayout.getChildren().clear();
        if(!inputSearch.getText().isBlank()) {
            showSearchInfo();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Please, fill the search field.",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    void showSearchInfo() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String getInfo = "SELECT * FROM movies WHERE lower(name) LIKE '%"+inputSearch.getText()+"%'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet rs = statement.executeQuery(getInfo);
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null,
                        "There is no such kind of film yet",
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
                inputSearch.clear();
            } else {
                while(rs.next()){
                    String datfilmname= rs.getString("name");
                    String datfilmposter= rs.getString("img");
                    VBox filmVbox=new VBox();
                    filmVbox.setPrefWidth(250);
                    ImageView filmimg=new ImageView();
                    javafx.scene.image.Image filmimage=new Image(getClass().getResourceAsStream("/images/image/"+datfilmposter),200,300,true,true);
                    filmimg.setImage(filmimage);
                    javafx.scene.control.Label filmname=new Label();
                    filmname.setFont(new Font("Arial",20));
                    filmname.setTextFill(javafx.scene.paint.Color.WHITE);
                    filmname.setPrefWidth(250);
                    filmname.setText(datfilmname);
                    filmVbox.getChildren().addAll(filmimg,filmname);
                    filmVbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Node node;
                            try {
                                FilmPage filmPage=new FilmPage();
                                setChoosennam(datfilmname);
                                filmPage.setChoosenfilmname(datfilmname);
                                node = (Node) FXMLLoader.load(HelloApplication.class.getResource("views/filmPage.fxml"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            filmload.getChildren().setAll(node);
                        }
                    });
                    System.out.println(datfilmname);
                    System.out.println(datfilmposter);

                    gridPaneLayout.setBackground(Background.fill(Color.TRANSPARENT));
                    gridPaneLayout.add(filmVbox,0,0);
                    GridPane.setMargin(filmVbox,new Insets(30));
                    inputSearch.clear();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}