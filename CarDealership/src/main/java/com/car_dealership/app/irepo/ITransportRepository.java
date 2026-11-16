package com.car_dealership.app.irepo;

import com.car_dealership.dom.entities.Transport;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.OfferStatus;
import com.car_dealership.dom.enums.TransportType;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.UUID;

public interface ITransportRepository {
    void save(Transport transport);

    Transport findById(UUID id);
    List<Transport> findByType(TransportType transportType);
    Transport findByVIN(String vin);
    Transport findBySPZ(String spz);
    List<Transport> findByBrand(String brand);
    List<Transport> findByModel(String model);
    List<Transport> findByBrandAndModel(String brand, String model);
    List<Transport> findInRangeAvgConsumption(double aCFrom, double aCTo);
    List<Transport> findInRangeYearOfManufacture(Year yOMFrom, Year yOMTo);
    List<Transport> findInRangeMileage(BigDecimal mileageFom, BigDecimal mileageTo);
    List<Transport> findByFuelType(FuelType fuelType);
    List<Transport> findByGearbox(String gearbox);
    List<Transport> findInRangeEngineCubicCapacity(double eCCFrom, double eCCTo);
    List<Transport> findInRangeEnginePower(int ePFrom, int ePTo);
    List<Transport> findByNumberOfSeats(int numberOfSeats);
    List<Transport> findInRangePrice(BigDecimal priceFrom, BigDecimal priceTo);
    List<Transport> findByOfferStatus(OfferStatus offerStatus);
    List<Transport> findAll();

    void delete(UUID id);
}
