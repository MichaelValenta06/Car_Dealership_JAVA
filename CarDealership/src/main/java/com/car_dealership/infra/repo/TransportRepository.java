package com.car_dealership.infra.repo;

import com.car_dealership.app.irepo.ITransportRepository;
import com.car_dealership.dom.entities.Transport;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.OfferStatus;
import com.car_dealership.dom.enums.TransportType;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransportRepository implements ITransportRepository {

    private final List<Transport> transports = new ArrayList<>();

    @Override
    public void save(Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport cannot be null.");
        }

        if (findById(transport.getId()) != null) {
            throw new IllegalArgumentException("Transport already exists.");
        }

        if (findByVIN(transport.getVIN()) != null) {
            throw new IllegalArgumentException("Transport with this VIN already exists.");
        }

        if (transport.getSPZ() != null && findBySPZ(transport.getSPZ()) != null) {
            throw new IllegalArgumentException("Transport with this SPZ already exists.");
        }

        transports.add(transport);
    }

    @Override
    public Transport findById(UUID id) {
        if (id == null) {
            return null;
        }

        return transports.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Transport> findByType(TransportType transportType) {
        return transports.stream()
                .filter(t -> t.getTransportType() == transportType)
                .toList();
    }

    @Override
    public Transport findByVIN(String vin) {
        if (vin == null) {
            return null;
        }

        return transports.stream()
                .filter(t -> t.getVIN().equalsIgnoreCase(vin))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Transport findBySPZ(String spz) {
        if (spz == null) {
            return null;
        }

        return transports.stream()
                .filter(t -> t.getSPZ() != null)
                .filter(t -> t.getSPZ().equalsIgnoreCase(spz))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Transport> findByBrand(String brand) {
        return transports.stream()
                .filter(t -> t.getBrand().equalsIgnoreCase(brand))
                .toList();
    }

    @Override
    public List<Transport> findByModel(String model) {
        return transports.stream()
                .filter(t -> t.getModel().equalsIgnoreCase(model))
                .toList();
    }

    @Override
    public List<Transport> findByBrandAndModel(String brand, String model) {
        return transports.stream()
                .filter(t -> t.getBrand().equalsIgnoreCase(brand))
                .filter(t -> t.getModel().equalsIgnoreCase(model))
                .toList();
    }

    @Override
    public List<Transport> findInRangeAvgConsumption(double aCFrom, double aCTo) {
        return transports.stream()
                .filter(t -> t.getAverageConsumption() >= aCFrom
                        && t.getAverageConsumption() <= aCTo)
                .toList();
    }

    @Override
    public List<Transport> findInRangeYearOfManufacture(Year yOMFrom, Year yOMTo) {
        return transports.stream()
                .filter(t -> !t.getYearOfManufacture().isBefore(yOMFrom)
                        && !t.getYearOfManufacture().isAfter(yOMTo))
                .toList();
    }

    @Override
    public List<Transport> findInRangeMileage(BigDecimal mileageFrom, BigDecimal mileageTo) {
        return transports.stream()
                .filter(t -> t.getMileage().compareTo(mileageFrom) >= 0
                        && t.getMileage().compareTo(mileageTo) <= 0)
                .toList();
    }

    @Override
    public List<Transport> findByFuelType(FuelType fuelType) {
        return transports.stream()
                .filter(t -> t.getFuelType() == fuelType)
                .toList();
    }

    @Override
    public List<Transport> findByGearbox(String gearbox) {
        return transports.stream()
                .filter(t -> t.getGearbox().equalsIgnoreCase(gearbox))
                .toList();
    }

    @Override
    public List<Transport> findInRangeEngineCubicCapacity(double eCCFrom, double eCCTo) {
        return transports.stream()
                .filter(t -> t.getEngineCubicCapacity() >= eCCFrom
                        && t.getEngineCubicCapacity() <= eCCTo)
                .toList();
    }

    @Override
    public List<Transport> findInRangeEnginePower(int ePFrom, int ePTo) {
        return transports.stream()
                .filter(t -> t.getEnginePower() >= ePFrom
                        && t.getEnginePower() <= ePTo)
                .toList();
    }

    @Override
    public List<Transport> findByNumberOfSeats(int numberOfSeats) {
        return transports.stream()
                .filter(t -> t.getNumberOfSeats() == numberOfSeats)
                .toList();
    }

    @Override
    public List<Transport> findInRangePrice(BigDecimal priceFrom, BigDecimal priceTo) {
        return transports.stream()
                .filter(t -> t.getPrice().compareTo(priceFrom) >= 0
                        && t.getPrice().compareTo(priceTo) <= 0)
                .toList();
    }

    @Override
    public List<Transport> findByOfferStatus(OfferStatus offerStatus) {
        return transports.stream()
                .filter(t -> t.getOfferStatus() == offerStatus)
                .toList();
    }

    @Override
    public List<Transport> findAll() {
        return new ArrayList<>(transports);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        transports.removeIf(t -> t.getId().equals(id));
    }
}
