package com.car_dealership.app.irepo;

import com.car_dealership.dom.entities.Car;
import com.car_dealership.dom.enums.CarBodyType;

import java.util.List;

public interface ICarRepository{
    void save(Car car);
    List<Car> findByBodyType(CarBodyType bodyType);
    List<Car> findByNumberOfDoors(int numberOfDoors);
    List<Car> findAllCars();
}
