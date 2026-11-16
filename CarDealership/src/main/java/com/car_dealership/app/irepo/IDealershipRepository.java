package com.car_dealership.app.irepo;

import com.car_dealership.dom.entities.Dealership;

import java.util.List;
import java.util.UUID;

public interface IDealershipRepository {
    void save(Dealership dealership);
    Dealership findById(UUID id);
    List<Dealership> findAll();
    void delete(UUID id);
}
