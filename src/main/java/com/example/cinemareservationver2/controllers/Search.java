package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    @FXML
    private TextField inputSearch;

    @FXML
    private GridPane gridPaneLayout;

    @FXML
    void showSearchInfo(javafx.event.ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        String getInfo = "SELECT * FROM movies WHERE lower(name) LIKE '%"+inputSearch.getText()+"%'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet rs = statement.executeQuery(getInfo);
            while(rs.next()){
                String output = rs.getString("name");
                if(output!=null){
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
                    System.out.println(datfilmname);
                    System.out.println(datfilmposter);

                    gridPaneLayout.setBackground(Background.fill(Color.TRANSPARENT));
                    gridPaneLayout.add(filmVbox,0,0);
                    GridPane.setMargin(filmVbox,new Insets(30));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "There is no such kind of film yet",
                            "Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
