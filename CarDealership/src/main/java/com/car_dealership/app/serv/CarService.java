
package com.car_dealership.app.serv;

import com.car_dealership.app.irepo.ICarRepository;
import com.car_dealership.dom.entities.Car;
import com.car_dealership.dom.enums.CarBodyType;
import com.car_dealership.dom.enums.FuelType;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public class CarService {

    private final ICarRepository repository;

    public CarService(ICarRepository repository) {
        this.repository = repository;
    }

    public Car createCar(
            String vin,
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

        Car car = new Car(
                vin,
                spz,
                brand,
                model,
                averageConsumption,
                yearOfManufacture,
                mileage,
                fuelType,
                price,
                bodyType,
                gearbox,
                numberOfDoors,
                numberOfSeats,
                engineCubicCapacity,
                enginePower
        );

        repository.save(car);

        return car;
    }

    public List<Car> findAllCars() {
        return repository.findAllCars();
    }

    public List<Car> findByBodyType(CarBodyType bodyType) {
        return repository.findByBodyType(bodyType);
    }

    public List<Car> findByNumberOfDoors(int numberOfDoors) {
        return repository.findByNumberOfDoors(numberOfDoors);
    }
}
