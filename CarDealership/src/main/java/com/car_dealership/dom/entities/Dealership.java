package com.car_dealership.dom.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Dealership {
    private final UUID id;
    private String name;
    private String address;
    private List<Transport> transports;
    private double rating;

    public Dealership(String name,
                      String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.transports = new ArrayList<>();
        this.rating = 0.0;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Transport> getTransport() {
        return transports;
    }

    public void addTransport(Transport newTransport) {
        transports.add(newTransport);
    }

    public int getTransportCount() {
        return transports.size();
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void removeTransport(UUID transportId) {
        transports.removeIf(transport ->
                transport.getId().equals(transportId)
        );
    }

}
