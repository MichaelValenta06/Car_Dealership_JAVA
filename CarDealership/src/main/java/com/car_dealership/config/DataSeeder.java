package com.car_dealership.config;

import com.car_dealership.app.serv.CarService;
import com.car_dealership.app.serv.MotorbikeService;
import com.car_dealership.app.serv.DealershipService;
import com.car_dealership.dom.entities.Car;
import com.car_dealership.dom.entities.Dealership;
import com.car_dealership.dom.entities.Motorbike;
import com.car_dealership.dom.enums.CarBodyType;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.MotorbikeType;

import java.math.BigDecimal;
import java.time.Year;

public class DataSeeder {

    private final CarService carService;
    private final MotorbikeService motorbikeService;
    private final DealershipService dealershipService;

    public DataSeeder(
            CarService carService,
            MotorbikeService motorbikeService,
            DealershipService dealershipService
    ) {
        this.carService = carService;
        this.motorbikeService = motorbikeService;
        this.dealershipService = dealershipService;
    }

    public void seed() {

        if (!dealershipService.getAllDealerships().isEmpty()) {
            return;
        }

        Dealership centralMotors = dealershipService.createDealership(
                "Central Motors",
                "Václavské náměstí 10, Praha"
        );

        Dealership premiumVehicles = dealershipService.createDealership(
                "Premium Vehicles",
                "Masarykova 25, Brno"
        );

        seedCentralMotors(centralMotors);
        seedPremiumVehicles(premiumVehicles);
    }

    private void seedCentralMotors(Dealership dealership) {

        addCar(dealership,
                "WVWZZZ1JZXW000001", "1AB1234",
                "Volkswagen", "Golf", 5.8, 2020,
                "65000", FuelType.GASOLINE, "429000",
                CarBodyType.HATCHBACK, "Manual", 5, 5, 1498, 110);

        addCar(dealership,
                "WBA8E9C50JA000002", "2AB2345",
                "BMW", "320d", 4.9, 2019,
                "92000", FuelType.DIESEL, "589000",
                CarBodyType.SEDAN, "Automatic", 4, 5, 1995, 140);

        addCar(dealership,
                "TMBJG7NE5L0000003", "3AB3456",
                "Skoda", "Octavia", 5.2, 2021,
                "48000", FuelType.GASOLINE, "499000",
                CarBodyType.STATION_WAGON, "Automatic", 5, 5, 1498, 110);

        addCar(dealership,
                "WDD2050401A000004", "4AB4567",
                "Mercedes-Benz", "C-Class", 6.1, 2018,
                "105000", FuelType.DIESEL, "620000",
                CarBodyType.SEDAN, "Automatic", 4, 5, 2143, 125);

        addCar(dealership,
                "VF1RFB00600000005", "5AB5678",
                "Renault", "Megane", 5.5, 2022,
                "32000", FuelType.GASOLINE, "379000",
                CarBodyType.HATCHBACK, "Manual", 5, 5, 1332, 103);

        addCar(dealership,
                "JHMFC1650KS000006", "6AB6789",
                "Honda", "Civic", 6.0, 2019,
                "78000", FuelType.GASOLINE, "459000",
                CarBodyType.HATCHBACK, "Manual", 5, 5, 1498, 134);


        addMotorbike(dealership,
                "JH2SC5900MK000007", "7AB7890",
                "Honda", "CB650R", 4.9, 2021,
                "12000", FuelType.GASOLINE, "Manual",
                649, 70, 2, "189000",
                MotorbikeType.ROAD_and_SPORT);

        addMotorbike(dealership,
                "WB10A0100LZ000008", "8AB8901",
                "BMW", "R1250GS", 4.8, 2020,
                "18500", FuelType.GASOLINE, "Manual",
                1254, 100, 2, "359000",
                MotorbikeType.TOURING_and_ADVENTURE);

        addMotorbike(dealership,
                "JYARN4310MA000009", "9AB9012",
                "Yamaha", "MT-07", 4.2, 2022,
                "8000", FuelType.GASOLINE, "Manual",
                689, 54, 2, "169000",
                MotorbikeType.ROAD_and_SPORT);

        addMotorbike(dealership,
                "ZDM1RABW0KB000010", "1AC0123",
                "Ducati", "Scrambler", 5.0, 2019,
                "22000", FuelType.GASOLINE, "Manual",
                803, 54, 2, "199000",
                MotorbikeType.CITY_and_LIGHT);
    }

    private void seedPremiumVehicles(Dealership dealership) {

        addCar(dealership,
                "WAUZZZF48KA000011", "2AC1234",
                "Audi", "A4", 6.2, 2020,
                "56000", FuelType.DIESEL, "679000",
                CarBodyType.SEDAN, "Automatic", 4, 5, 1968, 140);

        addCar(dealership,
                "WBA7T0100LG000012", "3AC2345",
                "BMW", "X5", 8.1, 2021,
                "42000", FuelType.DIESEL, "1299000",
                CarBodyType.SUV, "Automatic", 5, 5, 2993, 210);

        addCar(dealership,
                "ZFA3120000J000013", "4AC3456",
                "Fiat", "500", 4.7, 2022,
                "18000", FuelType.GASOLINE, "299000",
                CarBodyType.HATCHBACK, "Manual", 3, 4, 999, 51);

        addCar(dealership,
                "KMHXX81BPJU000014", "5AC4567",
                "Hyundai", "Tucson", 6.8, 2020,
                "67000", FuelType.DIESEL, "599000",
                CarBodyType.SUV, "Automatic", 5, 5, 1995, 136);

        addCar(dealership,
                "YS3FD79Y166000015", "6AC5678",
                "Saab", "9-3", 7.2, 2008,
                "145000", FuelType.GASOLINE, "159000",
                CarBodyType.CONVERTIBLE, "Manual", 2, 4, 1998, 110);

        addCar(dealership,
                "1HGCV1F30KA000016", "7AC6789",
                "Honda", "Accord", 6.5, 2019,
                "83000", FuelType.GASOLINE, "549000",
                CarBodyType.SEDAN, "Automatic", 4, 5, 1498, 143);

        addMotorbike(dealership,
                "JYAVP27E0LA000017", "8AC7890",
                "Yamaha", "Tracer 900", 5.2, 2020,
                "16000", FuelType.GASOLINE, "Manual",
                847, 85, 2, "229000",
                MotorbikeType.TOURING_and_ADVENTURE);

        addMotorbike(dealership,
                "ZDM1RABW0NB000018", "9AC8901",
                "Ducati", "Monster", 5.5, 2022,
                "9000", FuelType.GASOLINE, "Manual",
                937, 82, 2, "259000",
                MotorbikeType.ROAD_and_SPORT);

        addMotorbike(dealership,
                "JH2RC7400MK000019", "1AD9012",
                "Honda", "Africa Twin", 4.8, 2021,
                "14000", FuelType.GASOLINE, "Manual",
                1084, 75, 2, "289000",
                MotorbikeType.TOURING_and_ADVENTURE);

        addMotorbike(dealership,
                "VBKJRA403LM000020", "2AD0123",
                "KTM", "390 Duke", 3.8, 2023,
                "4500", FuelType.GASOLINE, "Manual",
                373, 32, 2, "139000",
                MotorbikeType.CITY_and_LIGHT);
    }

    private void addCar(
            Dealership dealership,
            String vin,
            String spz,
            String brand,
            String model,
            double consumption,
            int year,
            String mileage,
            FuelType fuelType,
            String price,
            CarBodyType bodyType,
            String gearbox,
            int doors,
            int seats,
            double engineCapacity,
            int enginePower
    ) {
        Car car = carService.createCar(
                vin,
                spz,
                brand,
                model,
                consumption,
                Year.of(year),
                new BigDecimal(mileage),
                fuelType,
                new BigDecimal(price),
                bodyType,
                gearbox,
                doors,
                seats,
                engineCapacity,
                enginePower
        );

        dealership.addTransport(car);
    }

    private void addMotorbike(
            Dealership dealership,
            String vin,
            String spz,
            String brand,
            String model,
            double consumption,
            int year,
            String mileage,
            FuelType fuelType,
            String gearbox,
            double engineCapacity,
            int enginePower,
            int seats,
            String price,
            MotorbikeType motorbikeType
    ) {
        Motorbike motorbike = motorbikeService.createMotorbike(
                vin,
                spz,
                brand,
                model,
                consumption,
                Year.of(year),
                new BigDecimal(mileage),
                fuelType,
                gearbox,
                engineCapacity,
                enginePower,
                seats,
                new BigDecimal(price),
                motorbikeType
        );

        dealership.addTransport(motorbike);
    }
}