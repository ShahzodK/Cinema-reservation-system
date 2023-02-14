package com.example.cinemareservationver2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.text.Style;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FilmPage implements Initializable {
    @FXML
    private VBox Bookingpane;
    @FXML
    private HBox filmviewbox;
    @FXML
    private VBox choosenfilm;
    public static String getChoosenfilmname() {
        return choosenfilmname;
    }

    public static void setChoosenfilmname(String choosenfilmname) {
        FilmPage.choosenfilmname = choosenfilmname;
    }

    public static String choosenfilmname="3";
    public static String choosenfilmimg="";
    public static String choosenfilmcountry="";
    public static String choosenfilmgenre="";
    public static int choosenfilmimdb=0;
    public static int choosenfilmyear=0;
    public static String choosenfilmdescription="";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();
        Statement statement= null;
        try {
            String choosenfilmdata="SELECT * FROM movies WHERE name='"+choosenfilmname+"';";
            statement = connectionDB.createStatement();
            ResultSet rschfd= statement.executeQuery(choosenfilmdata);
            rschfd.next();
            choosenfilmimg=rschfd.getString("img");
            choosenfilmgenre=rschfd.getString("genre");
            choosenfilmcountry=rschfd.getString("country");
            choosenfilmyear=rschfd.getInt("year");
            choosenfilmimdb=rschfd.getInt("imdb");
            choosenfilmdescription=rschfd.getString("description");

            HBox posterinforms=new HBox();
            posterinforms.setPrefHeight(400);
            posterinforms.setPrefWidth(700);

            ImageView poster = new ImageView();
            Image image=new Image(getClass().getResourceAsStream("images/image/"+choosenfilmimg),250,400,true,true);
            poster.setImage(image);

            Label name = new Label();
            name.setFont(new Font("Arial", 30));
            name.setText(choosenfilmname);
            name.setStyle("-fx-text-fill: green;");

            Label genre = new Label();
            genre.setFont(new Font("Arial",  25));
            genre.setStyle("-fx-text-fill: green;");
            genre.setText(choosenfilmgenre);

            Label country = new Label();
            country.setFont(new Font("Arial", 30));
            country.setText(choosenfilmcountry);
            country.setStyle("-fx-text-fill: green;");

            Label year = new Label();
            year.setFont(new Font("Arial", 30));
            year.setText(choosenfilmyear+"");
            year.setStyle("-fx-text-fill: green;");

            Label score = new Label();
            score.setFont(new Font("Arial", 30));
            score.setText(choosenfilmimdb+"/10");
            score.setStyle("-fx-text-fill: green;");

            Text description=new Text();
            description.setWrappingWidth(400);
            description.setFont(new Font("Arial",22));
            description.setText(choosenfilmdescription);

            Separator separatorposterinform=new Separator();
            separatorposterinform.setOrientation(Orientation.VERTICAL);
            separatorposterinform.setMaxHeight(300);

            VBox informantions=new VBox();
            informantions.setPrefWidth(400);
            informantions.setPrefHeight(400);

            Separator separatorinform=new Separator();
            separatorinform.setOrientation(Orientation.HORIZONTAL);
            separatorinform.setMaxWidth(400);
            separatorinform.setMinWidth(30);

            Separator separatorinform2=new Separator();
            separatorinform2.setOrientation(Orientation.HORIZONTAL);
            separatorinform2.setMaxWidth(400);
            separatorinform2.setMinWidth(30);

            Bookingpane.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));


            informantions.getChildren().addAll(name,separatorinform,genre,country,year,score,separatorinform2,description);
            filmviewbox.getChildren().addAll(poster,separatorposterinform,informantions);
            filmviewbox.setAlignment(Pos.CENTER);
            choosenfilm.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
            choosenfilm.setSpacing(15);

            Label bookingtitle=new Label();
            bookingtitle.setText("BOOK THE TICKET");
            bookingtitle.setFont(new Font("Arial", 35));
            bookingtitle.setStyle("-fx-text-fill: green;");

            Bookingpane.getChildren().add(bookingtitle);
            Bookingpane.setAlignment(Pos.BASELINE_CENTER);
            ComboBox combocinema=new ComboBox<>();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

