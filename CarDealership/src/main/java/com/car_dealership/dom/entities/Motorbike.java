package com.car_dealership.dom.entities;

import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.MotorbikeType;
import com.car_dealership.dom.enums.TransportType;

import java.math.BigDecimal;
import java.time.Year;

public class Motorbike extends Transport{
    private final MotorbikeType motorbikeType;

    public Motorbike(String vin,
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
                     BigDecimal price,
                     MotorbikeType motorbikeType) {
        super(TransportType.MOTORBIKE,
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
                price);

        this.motorbikeType = motorbikeType;
    }

    public MotorbikeType getMotorbikeType() {
        return motorbikeType;
    }
}
