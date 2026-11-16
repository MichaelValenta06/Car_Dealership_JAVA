package com.car_dealership.dom.entities;

import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.OfferStatus;
import com.car_dealership.dom.enums.TransportType;

import java.math.BigDecimal;
import java.time.Year;
import java.util.UUID;

public abstract class Transport {
    protected final UUID id;
    protected final TransportType transportType;
    protected final String vin;
    protected String spz;
    protected final String brand;
    protected final String model;
    protected double averageConsumption;
    protected final Year yearOfManufacture;
    protected BigDecimal mileage;
    protected FuelType fuelType;
    protected String gearbox;
    protected double engineCubicCapacity;
    protected int enginePower;
    protected int numberOfSeats;
    protected BigDecimal price;
    protected OfferStatus offerStatus;

    public Transport(TransportType transportType,
                     String vin,
                     String spz,
                     String brand,
                     String model,
                     double averageConsumption,
                     Year yearOfManufacture,
                     BigDecimal mileage,
                     FuelType fuelType,
                     String gearbox,
                     double engineCubicCapacity,
                     int enginePower,
                     int numberOfSeats,
                     BigDecimal price) {
        this.id = UUID.randomUUID();
        this.transportType = transportType;
        this.vin = vin;
        this.spz = spz;
        this.brand = brand;
        this.model = model;
        this.averageConsumption = averageConsumption;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.gearbox = gearbox;
        this.engineCubicCapacity = engineCubicCapacity;
        this.enginePower = enginePower;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.offerStatus = OfferStatus.AVAILABLE;
    }

    public UUID getId() {
        return id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getVIN() {
        return vin;
    }

    public String getSPZ() {
        return spz;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Year getYearOfManufacture() {
        return yearOfManufacture;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public double getEngineCubicCapacity() {
        return engineCubicCapacity;
    }

    public void setEngineCubicCapacity(double engineCubicCapacity) {
        this.engineCubicCapacity = engineCubicCapacity;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OfferStatus getOfferStatus(){
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus status){
        this.offerStatus = status;
    }
}
