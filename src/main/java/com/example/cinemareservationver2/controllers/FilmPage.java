package com.example.cinemareservationver2.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FilmPage implements Initializable {

    @FXML
    private HBox filmviewbox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        HBox posterinforms=new HBox();
        posterinforms.setPrefHeight(400);
        posterinforms.setPrefWidth(700);
        ImageView poster = new ImageView();
        Label name = new Label();
        name.setFont(new Font("Arial", 25));
        name.setText("OLIMJON");
        Image image=new Image(getClass().getResourceAsStream("images/image/avatar.jpg"),200,200,true,true);
        poster.setImage(image);
        filmviewbox.getChildren().addAll(poster,name);

    }

