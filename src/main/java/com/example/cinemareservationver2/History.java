package com.example.cinemareservationver2;

import com.example.cinemareservationver2.DatabaseConnection;

import java.sql.Connection;

public class History {
    public void historygenerator(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

    }
}
