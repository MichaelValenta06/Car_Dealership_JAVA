package com.car_dealership.dom.entities;

import com.car_dealership.dom.enums.CarBodyType;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.TransportType;

import java.math.BigDecimal;
import java.time.Year;

public class Car extends Transport{
    private final CarBodyType bodyType;
    private int numberOfDoors;

    public Car(String vin,
            String spz,
            String brand,
            String model,
            double averageConsumption,
            Year yearOfManufacture,
            BigDecimal mileage,
            FuelType fuelType,
            BigDecimal price,
            CarBodyType bodyType,
            String gearbox,
            int numberOfDoors,
            int numberOfSeats,
            double engineCubicCapacity,
            int enginePower
    ) {
        super(
                TransportType.CAR,
                vin,
                spz,
                brand,
                model,
                averageConsumption,
                yearOfManufacture,
                mileage,
                fuelType,
                gearbox,
                engineCubicCapacity,
                enginePower,
                numberOfSeats,
                price
        );

        this.bodyType = bodyType;
        this.numberOfDoors = numberOfDoors;
    }

    public CarBodyType getBodyType() {
        return bodyType;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

}
