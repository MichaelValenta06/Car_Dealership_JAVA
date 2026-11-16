package com.car_dealership.app.irepo;

import com.car_dealership.dom.entities.Motorbike;
import com.car_dealership.dom.enums.MotorbikeType;

import java.util.List;

public interface IMotorbikeRepository{
    void save(Motorbike motorbike);
    List<Motorbike> findByMotorbikeType(MotorbikeType motorbikeType);
    List<Motorbike> findAllMotorbikes();
}
