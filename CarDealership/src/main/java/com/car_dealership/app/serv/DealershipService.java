package com.car_dealership.app.serv;

import com.car_dealership.app.irepo.IDealershipRepository;
import com.car_dealership.dom.entities.Dealership;

import java.util.List;
import java.util.UUID;

public class DealershipService {

    private final IDealershipRepository repository;

    public DealershipService(IDealershipRepository repository) {
        this.repository = repository;
    }

    public Dealership createDealership(String name, String address) {

        Dealership dealership = new Dealership(name, address);

        repository.save(dealership);

        return dealership;
    }

    public Dealership getDealership(UUID id) {
        return repository.findById(id);
    }

    public List<Dealership> getAllDealerships() {
        return repository.findAll();
    }

    public void removeTransportFromAllDealerships(UUID transportId) {
        for (Dealership dealership : repository.findAll()) {
            dealership.removeTransport(transportId);
        }
    }
}
