package com.example.cinemareservationver2.controllers;

import com.example.cinemareservationver2.FilmPage;
import com.example.cinemareservationver2.HelloApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Search implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        try{
//
//            Statement statement=connectionDB.createStatement();
//            String datafilmname="SELECT * FROM movies ORDER BY id DESC;";
//            ResultSet rs= statement.executeQuery(datafilmname);
//
//            int column=0;
//            int row=1;
//            for(int j=0;j<7;j++){
//                rs.next();
//                String datfilmname= rs.getString("name");
//                String datfilmposter= rs.getString("img");
//
//                VBox filmVbox=new VBox();
//                filmVbox.setPrefWidth(250);
//                ImageView filmimg=new ImageView();
//                Image filmimage=new Image(getClass().getResourceAsStream("images/image/"+datfilmposter),300,400,true,true);
//                filmimg.setImage(filmimage);
//                Label filmname=new Label();
//                filmname.setFont(new Font("Arial",20));
//                filmname.setTextFill(Color.WHITE);
//                filmname.setPrefWidth(250);
//                filmname.setText(datfilmname);
//                filmVbox.getChildren().addAll(filmimg,filmname);
//
//                if(column==4){
//                    column=0;
//                    ++row;
//                }
//                gridPaneLayout.setBackground(Background.fill(Color.TRANSPARENT));
//                gridPaneLayout.add(filmVbox,column++,row);
//                GridPane.setMargin(filmVbox,new Insets(10));
//                filmVbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        Node node;
//                        try {
//                            FilmPage filmPage=new FilmPage();
//                            setChoosennam(datfilmname);
//                            filmPage.setChoosenfilmname(datfilmname);
//                            node = (Node) FXMLLoader.load(HelloApplication.class.getResource("views/filmPage.fxml"));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                        filmLoad.getChildren().setAll(node);
//                    }
//                });
//
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }

    }
}
