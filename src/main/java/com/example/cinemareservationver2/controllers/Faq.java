package com.example.cinemareservationver2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Faq implements Initializable {
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate minDate = LocalDate.of(2023, 2, 1);
        LocalDate maxDate = LocalDate.now();
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(minDate)&item.isAfter(maxDate));
                    }});
    }
}
