package com.example.cinemareservationver2;

public class Order {
    private int id, ticketNumber, numberOfTickets, price;
    private String movieName, time;
    public Order(int id, String movieName, String time, int ticketNumber, int numberOfTickets, int price){
        this.id = id;
        this.movieName = movieName;
        this.time = time;
        this.ticketNumber = ticketNumber;
        this.numberOfTickets = numberOfTickets;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getPrice() {
        return price;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getTime() {
        return time;
    }
}
