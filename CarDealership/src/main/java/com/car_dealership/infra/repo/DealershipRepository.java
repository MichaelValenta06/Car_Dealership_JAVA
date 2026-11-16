package com.car_dealership.infra.repo;

import com.car_dealership.app.irepo.IDealershipRepository;
import com.car_dealership.dom.entities.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DealershipRepository implements IDealershipRepository {
    private final List<Dealership> dealerships = new ArrayList<>();

    @Override
    public void save(Dealership dealership) {
        dealerships.add(dealership);
    }

    @Override
    public Dealership findById(UUID id) {
        return dealerships.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Dealership> findAll() {
        return new ArrayList<>(dealerships);
    }

    @Override
    public void delete(UUID id) {
        dealerships.removeIf(d -> d.getId().equals(id));
    }
}
