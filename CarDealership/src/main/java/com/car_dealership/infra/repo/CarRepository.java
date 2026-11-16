package com.car_dealership.infra.repo;

import com.car_dealership.app.irepo.ICarRepository;
import com.car_dealership.app.irepo.ITransportRepository;
import com.car_dealership.dom.entities.Car;
import com.car_dealership.dom.enums.CarBodyType;
import com.car_dealership.dom.enums.TransportType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository implements ICarRepository {

    private final ITransportRepository transportRepository;

    public CarRepository(ITransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public void save(Car car) {
        transportRepository.save(car);
    }

    @Override
    public List<Car> findByBodyType(CarBodyType bodyType) {
        return findAllCars().stream()
                .filter(c -> c.getBodyType() == bodyType)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Car> findByNumberOfDoors(int numberOfDoors) {
        return findAllCars().stream()
                .filter(c -> c.getNumberOfDoors() == numberOfDoors)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Car> findAllCars() {
        return transportRepository.findByType(TransportType.CAR)
                .stream()
                .filter(Car.class::isInstance)
                .map(Car.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
