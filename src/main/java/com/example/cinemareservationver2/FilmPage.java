package com.example.cinemareservationver2;
import com.example.cinemareservationver2.controllers.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.Style;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class FilmPage implements Initializable {
    Login user = new Login();

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
    public static int choosenfilmprice=0;

    public static String choosenfilmdescription="";
    public static String arraydataforpayment[][];


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
            choosenfilmprice=rschfd.getInt("price");


            HBox posterinforms=new HBox();
            posterinforms.setPrefHeight(400);
            posterinforms.setPrefWidth(700);

            ImageView poster = new ImageView();
            Image image=new Image(getClass().getResourceAsStream("images/image/"+choosenfilmimg),300,400,true,true);
            poster.setImage(image);

            Label name = new Label();
            name.setFont(new Font("Arial", 36));
            name.setText(choosenfilmname);
            name.setStyle("-fx-text-fill: white;");

            Label genre = new Label();
            genre.setFont(new Font("Arial",  25));
            genre.setStyle("-fx-text-fill: white;");
            genre.setText(choosenfilmgenre);

            Label country = new Label();
            country.setFont(new Font("Arial", 25));
            country.setText(choosenfilmcountry);
            country.setStyle("-fx-text-fill: white;");

            Label year = new Label();
            year.setFont(new Font("Arial", 25));
            year.setText(choosenfilmyear+"");
            year.setStyle("-fx-text-fill: white;");

            Label score = new Label();
            score.setFont(new Font("Arial", 32));
            score.setText(choosenfilmimdb+"/10");
            score.setStyle("-fx-text-fill: white;");

            Text description=new Text();
            description.setWrappingWidth(400);
            description.setFont(new Font("Arial",25));
            description.setText(choosenfilmdescription);
            description.setStyle("-fx-fill: white");

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
            filmviewbox.getChildren().addAll(poster,informantions);
            filmviewbox.setAlignment(Pos.CENTER);
            choosenfilm.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
            choosenfilm.setSpacing(15);
//////////////////////////////////////Booking//////////////////
            Bookingpane.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

            Label bookingtitle=new Label();
            bookingtitle.setText("BOOK THE TICKET");
            bookingtitle.setFont(new Font("Arial", 35));
            bookingtitle.setStyle("-fx-text-fill: white;");

            HBox cinemachoose=new HBox();
            cinemachoose.setAlignment(Pos.CENTER);
            Separator separatorinform3=new Separator();
            separatorinform3.setOrientation(Orientation.HORIZONTAL);
            separatorinform3.setMaxWidth(100);
            Label choosingcinemaname=new Label();
            choosingcinemaname.setText("Number of tickets");
            choosingcinemaname.setFont(new Font("Arial", 30));
            choosingcinemaname.setStyle("-fx-text-fill: white;");
            javafx.scene.control.TextField textField=new javafx.scene.control.TextField();
            textField.setPromptText("how many tickets do you want?");
            textField.setFont(new Font("Arial",22));
            cinemachoose.getChildren().addAll(choosingcinemaname);
            javafx.scene.control.Button buttonbook=new javafx.scene.control.Button("Confirm");
            Label messagelabel=new Label();
            messagelabel.setFont(new Font("Arial",22));
            messagelabel.setText("");
            Label labelprice=new Label();
            labelprice.setFont(new Font("Arial",20));
            labelprice.setText("");
            javafx.scene.control.Button pay =new Button("Pay");
            buttonbook.setOnAction(event -> {
                String numberoftickets=textField.getText();
                int numberofticketsint=Integer.parseInt(numberoftickets);
                arraydataforpayment=new String[numberofticketsint][3];
                for(int i=0;i<numberofticketsint;i++){
                    int leftLimit = 48; // letter 'a'
                    int rightLimit = 57; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();
                    StringBuilder buffer = new StringBuilder(targetStringLength);
                    for (int j = 0; j < targetStringLength; j++) {
                        int randomLimitedInt = leftLimit + (int)
                                (random.nextFloat() * (rightLimit - leftLimit + 1));
                        buffer.append((char) randomLimitedInt);
                    }
                    String generatedString = buffer.toString();
                    arraydataforpayment[i][0]= user.getUsername();
                    arraydataforpayment[i][1]=choosenfilmname;
                    arraydataforpayment[i][2]=generatedString;

                    System.out.println(generatedString);
                    System.out.println(user.getUsername());
                    System.out.println(choosenfilmname);
//                    String querybooking="INSERT INTO orders(username, moviename, ticketid) VALUES ('"+user.getUsername()+"', '"+choosenfilmname+"', '"+generatedString+"');";
//                    try {
//                        Statement ticketgenerat=connectionDB.createStatement();
//                        ticketgenerat.executeUpdate(querybooking);
//
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }

                }
//                Label labelbooked= new Label();
//                labelbooked.setFont(new Font("Arial",12));
//                for(int l=0;l<numberofticketsint;l++){
//                    labelbooked.setText("You are buying tickets for "+arraydataforpayment[l][1]+" film with id: "+arraydataforpayment[l][2]);
//                    Bookingpane.getChildren().add(labelbooked);
//                    System.out.println(arraydataforpayment.length);
//                }
                labelprice.setText("Total price is: "+numberofticketsint*choosenfilmprice+"$");
                messagelabel.setText("Your tickets are ready, Pay for them:");
                Bookingpane.getChildren().add(labelprice);
                Bookingpane.getChildren().add(messagelabel);
                Bookingpane.getChildren().add(pay);
            });
            pay.setOnAction(event -> {
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/Payment.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setScene(scene);
                primaryStage.show();
            });

            Bookingpane.setSpacing(15);
            Bookingpane.getChildren().addAll(bookingtitle,separatorinform3,cinemachoose,textField,buttonbook);
            Bookingpane.setAlignment(Pos.BASELINE_CENTER);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

