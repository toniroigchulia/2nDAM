package com.mongodb;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class Booking {
    private int locationNumber;
    private String client;
    private String agency;
    private double price;
    private String room;
    private String hotel;
    private String checkIn;
    private int roomNights;
    
    // Getters and setters
    public int getLocationNumber() {
        return locationNumber;
    }
    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getHotel() {
        return hotel;
    }
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public int getRoomNights() {
        return roomNights;
    }
    public void setRoomNights(int roomNights) {
        this.roomNights = roomNights;
    }
}