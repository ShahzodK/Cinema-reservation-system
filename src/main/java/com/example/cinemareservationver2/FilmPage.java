package com.example.cinemareservationver2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.text.Style;
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
        Image image=new Image(getClass().getResourceAsStream("images/image/avatar.jpg"),250,400,true,true);
        poster.setImage(image);

        Label name = new Label();
        name.setFont(new Font("Arial", 25));
        name.setText("Avatar");

        Label genre = new Label();
        genre.setFont(new Font("Arial",  25));
        genre.setStyle("-fx-text-fill: green;");
        genre.setText("Fantastic");

        Label country = new Label();
        country.setFont(new Font("Arial", 25));
        country.setText("USA");

        Label year = new Label();
        year.setFont(new Font("Arial", 25));
        year.setText("2010");

        Label score = new Label();
        score.setFont(new Font("Arial", 25));
        score.setText("7/10");

        VBox informantions=new VBox();
        informantions.setPrefWidth(400);
        informantions.setPrefHeight(400);
        informantions.getChildren().addAll(name,genre,country,year,score);

        filmviewbox.getChildren().addAll(poster,informantions);

    }
}

