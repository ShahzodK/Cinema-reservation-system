package com.example.cinemareservationver2;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Browse implements Initializable {
    @FXML
    private AnchorPane filmLoad;
//    @FXML
//    private HBox cardLayout;
    @FXML

    private GridPane gridPaneLayout;

    public static String getChoosennam() {
        return choosennam;
    }

    public static void setChoosennam(String choosennam) {
        Browse.choosennam = choosennam;
    }

    public static String choosennam="1";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

///////////////////////////CARD FOR RECENTLY ADDED////////////////////////////////////
//        int id[] = new int[4];
//        try{
//            String dataid="SELECT id FROM movies ORDER BY id DESC LIMIT 4;";
//            Statement statement=connectionDB.createStatement();
//            ResultSet rs = statement.executeQuery(dataid);
//
//            rs.next();
//            for(int i=0;i<4;i++){
//                id[i]=rs.getInt("id");
//                rs.next();
//            }
//        }
//
//        catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//        for (int i=0;i<4;i++){
//            try {
//                Statement statement=connectionDB.createStatement();
//                String dataposter="SELECT img FROM movies WHERE id="+id[i]+";";
//                String dataname="SELECT name FROM movies WHERE id="+id[i]+";";
//                String datagenre="SELECT genre FROM movies WHERE id="+id[i]+";";
//                String datascore="SELECT imdb FROM movies WHERE id="+id[i]+";";
//
//                ResultSet rn = statement.executeQuery(dataname);
//                rn.next();
//                String datname= rn.getString("name");
//
//                ResultSet rg = statement.executeQuery(datagenre);
//                rg.next();
//                String datgenre= rg.getString("genre");
//
//                ResultSet rp = statement.executeQuery(dataposter);
//                rp.next();
//                String datposter= rp.getString("img");
//
//                ResultSet rz = statement.executeQuery(datascore);
//                rz.next();
//                String datscore= rz.getString("imdb");
//
//                HBox reccard=new HBox();
//                reccard.setId(datname);
//                reccard.setPrefHeight(200);
//                reccard.setPrefWidth(400);
//                reccard.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
//                VBox recvbox=new VBox();
//                recvbox.setPrefWidth(200);
//                recvbox.setPrefHeight(200);
//                recvbox.setMinWidth(200);
//                ImageView imageView=new ImageView();
//                Label recname=new Label();
//                recname.setPrefWidth(300);
//                recname.setFont(new Font("Arial",22));
//                Label recgenre=new Label();
//                recgenre.setPrefWidth(300);
//                recgenre.setFont(new Font("Arial",22));
//                Label recscore=new Label();
//                recscore.setPrefWidth(300);
//                recscore.setFont(new Font("Arial",22));
//                reccard.getChildren().addAll(imageView,recvbox);
//                recvbox.getChildren().addAll(recname,recgenre,recscore);
//                recvbox.setSpacing(20);
//                reccard.setSpacing(20);
//                Image image=new Image(getClass().getResourceAsStream("images/image/"+datposter),200,200,true,true);
//                imageView.setImage(image);
//                recname.setText(datname);
//                recgenre.setText(datgenre);
//                recscore.setText(datscore+"/10");
//                cardLayout.getChildren().add(reccard);
//                Separator separatorvert=new Separator();
//                separatorvert.setOrientation(Orientation.VERTICAL);
//                separatorvert.setPrefHeight(150);
//                cardLayout.setAlignment(Pos.CENTER_LEFT);
//                cardLayout.getChildren().add(separatorvert);
//                reccard.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        Node node;
//                        try {
//                            FilmPage filmPage=new FilmPage();
//                            setChoosennam(datname);
//                            filmPage.setChoosenfilmname(datname);
//                            node = (Node)FXMLLoader.load(HelloApplication.class.getResource("views/filmPage.fxml"));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                        filmLoad.getChildren().setAll(node);
//                    }
//                });
//
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                e.getCause();
//            }
//
//        }

//////////////////////////////CARD FOR ALL FILMS////////////////////////////////

        try{
            String filmnumberofrows="SELECT COUNT(*) FROM movies;";
            Statement filmrows=connectionDB.createStatement();
            ResultSet rsfr=filmrows.executeQuery(filmnumberofrows);
            rsfr.next();
            int filmnumbers=rsfr.getInt("COUNT(*)");
            Statement statement=connectionDB.createStatement();
            String datafilmname="SELECT * FROM movies ORDER BY id DESC;";
            ResultSet rs= statement.executeQuery(datafilmname);

            int column=0;
            int row=1;
            for(int j=0;j<filmnumbers;j++){
                rs.next();
                String datfilmname= rs.getString("name");
                String datfilmposter= rs.getString("img");

                VBox filmVbox=new VBox();
                filmVbox.setPrefWidth(250);
                ImageView filmimg=new ImageView();
                Image filmimage=new Image(getClass().getResourceAsStream("images/image/"+datfilmposter),300,400,true,true);
                filmimg.setImage(filmimage);
                Label filmname=new Label();
                filmname.setFont(new Font("Arial",20));
                filmname.setTextFill(Color.WHITE);
                filmname.setPrefWidth(250);
                filmname.setText(datfilmname);
                filmVbox.getChildren().addAll(filmimg,filmname);

                if(column==4){
                    column=0;
                    ++row;
                }
                gridPaneLayout.setBackground(Background.fill(Color.TRANSPARENT));
                gridPaneLayout.add(filmVbox,column++,row);
                GridPane.setMargin(filmVbox,new Insets(10));
                filmVbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node node;
                        try {
                            FilmPage filmPage=new FilmPage();
                            setChoosennam(datfilmname);
                            filmPage.setChoosenfilmname(datfilmname);
                            node = (Node)FXMLLoader.load(HelloApplication.class.getResource("views/filmPage.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        filmLoad.getChildren().setAll(node);
                    }
                });

            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

}