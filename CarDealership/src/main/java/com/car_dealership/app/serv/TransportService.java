package com.car_dealership.app.serv;

import com.car_dealership.app.irepo.ITransportRepository;
import com.car_dealership.dom.entities.Transport;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.OfferStatus;

import java.util.List;
import java.util.UUID;

public class TransportService {

    private final ITransportRepository repository;

    public TransportService(ITransportRepository repository) {
        this.repository = repository;
    }

    public Transport findById(UUID id) {
        return repository.findById(id);
    }

    public Transport findByVIN(String vin) {
        return repository.findByVIN(vin);
    }

    public Transport findBySPZ(String spz) {
        return repository.findBySPZ(spz);
    }

    public List<Transport> findByBrand(String brand) {
        return repository.findByBrand(brand);
    }

    public List<Transport> findByModel(String model) {
        return repository.findByModel(model);
    }

    public List<Transport> findByBrandAndModel(String brand, String model) {
        return repository.findByBrandAndModel(brand, model);
    }

    public List<Transport> findByFuelType(FuelType fuelType) {
        return repository.findByFuelType(fuelType);
    }

    public List<Transport> findByOfferStatus(OfferStatus offerStatus) {
        return repository.findByOfferStatus(offerStatus);
    }

    public List<Transport> findAll() {
        return repository.findAll();
    }

    public void updateOfferStatus(UUID id, OfferStatus status) {
        Transport transport = repository.findById(id);

        if (transport == null) {
            throw new IllegalArgumentException("Transport not found.");
        }

        transport.setOfferStatus(status);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
