package com.car_dealership.infra.repo;

import com.car_dealership.app.irepo.IMotorbikeRepository;
import com.car_dealership.app.irepo.ITransportRepository;
import com.car_dealership.dom.entities.Motorbike;
import com.car_dealership.dom.enums.MotorbikeType;
import com.car_dealership.dom.enums.TransportType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MotorbikeRepository implements IMotorbikeRepository {
    private final ITransportRepository transportRepository;

    public MotorbikeRepository(ITransportRepository transportRepository){
        this.transportRepository = transportRepository;
    }

    @Override
    public void save(Motorbike motorbike) {
        transportRepository.save(motorbike);
    }

    @Override
    public List<Motorbike> findByMotorbikeType(MotorbikeType motorbikeType) {
        return findAllMotorbikes().stream()
                .filter(m -> m.getMotorbikeType() == motorbikeType)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Motorbike> findAllMotorbikes() {
        return transportRepository.findByType(TransportType.MOTORBIKE)
                .stream()
                .filter(Motorbike.class::isInstance)
                .map(Motorbike.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
