package com.example.cinemares;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Topmovies {
    @FXML
    private Pane carousel;
    public void loadFxml () throws IOException {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("src/main/resources/com/example/cinemares/carousel.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        carousel.getChildren().add(newLoadedPane);
    }
}
