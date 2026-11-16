package com.car_dealership.app.serv;

import com.car_dealership.app.irepo.IMotorbikeRepository;
import com.car_dealership.dom.entities.Motorbike;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.MotorbikeType;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public class MotorbikeService {
    private final IMotorbikeRepository repository;

    public MotorbikeService(IMotorbikeRepository repository){
        this.repository = repository;
    }

    public Motorbike createMotorbike(
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
            BigDecimal price,
            MotorbikeType motorbikeType
    ){
        Motorbike motorbike = new Motorbike(
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
                price,
                motorbikeType
        );

        repository.save(motorbike);

        return motorbike;
    }

    public List<Motorbike> findAllMotorbikes(){
        return repository.findAllMotorbikes();
    }

    public List<Motorbike> findByMotorbikeType(MotorbikeType bikeType){
        return repository.findByMotorbikeType(bikeType);
    }
}
