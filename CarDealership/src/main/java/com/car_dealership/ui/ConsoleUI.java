package com.car_dealership.ui;

import com.car_dealership.app.serv.CarService;
import com.car_dealership.app.serv.MotorbikeService;
import com.car_dealership.app.serv.TransportService;
import com.car_dealership.app.serv.DealershipService;

import com.car_dealership.dom.entities.Car;
import com.car_dealership.dom.entities.Dealership;
import com.car_dealership.dom.entities.Motorbike;
import com.car_dealership.dom.entities.Transport;

import com.car_dealership.dom.enums.CarBodyType;
import com.car_dealership.dom.enums.FuelType;
import com.car_dealership.dom.enums.MotorbikeType;
import com.car_dealership.dom.enums.OfferStatus;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {

    private final Scanner scanner;

    private final CarService carService;
    private final MotorbikeService motorbikeService;
    private final TransportService transportService;
    private final DealershipService dealershipService;

    public ConsoleUI(
            CarService carService,
            MotorbikeService motorbikeService,
            TransportService transportService,
            DealershipService dealershipService
    ) {
        this.scanner = new Scanner(System.in);

        this.carService = carService;
        this.motorbikeService = motorbikeService;
        this.transportService = transportService;
        this.dealershipService = dealershipService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printHeader("CAR DEALERSHIP SYSTEM");

            System.out.println("1. Dealerships");
            System.out.println("2. Vehicles");
            System.out.println("0. Exit");

            int choice = readInt("Select option", 0, 2);

            switch (choice) {
                case 1 -> dealershipMenu();
                case 2 -> vehicleMenu();
                case 0 -> {
                    running = false;
                    System.out.println("\nGoodbye.");
                }
            }
        }
    }

    private void dealershipMenu() {
        boolean back = false;

        while (!back) {
            printHeader("DEALERSHIPS");

            System.out.println("1. List all dealerships");
            System.out.println("2. View dealership details");
            System.out.println("3. Create dealership");
            System.out.println("0. Back");

            int choice = readInt("Select option", 0, 3);

            switch (choice) {
                case 1 -> listDealerships();
                case 2 -> viewDealershipDetails();
                case 3 -> createDealership();
                case 0 -> back = true;
            }

            if (!back) {
                pause();
            }
        }
    }

    private void listDealerships() {
        printHeader("ALL DEALERSHIPS");

        List<Dealership> dealerships =
                dealershipService.getAllDealerships();

        if (dealerships.isEmpty()) {
            System.out.println("No dealerships found.");
            return;
        }

        for (int i = 0; i < dealerships.size(); i++) {
            Dealership dealership = dealerships.get(i);

            System.out.printf(
                    "%n%d. %s%n",
                    i + 1,
                    dealership.getName()
            );

            System.out.println("   Address: " + dealership.getAddress());
            System.out.println("   Vehicles: " + dealership.getTransportCount());
            System.out.println("   ID: " + dealership.getId());
        }
    }

    private void viewDealershipDetails() {
        Dealership dealership = selectDealership();

        if (dealership == null) {
            return;
        }

        printHeader(dealership.getName());

        System.out.println("ID: " + dealership.getId());
        System.out.println("Address: " + dealership.getAddress());
        System.out.println("Rating: " + dealership.getRating());
        System.out.println("Vehicle count: " + dealership.getTransportCount());

        System.out.println("\nVehicles:");
        displayTransports(dealership.getTransport());
    }

    private void createDealership() {
        printHeader("CREATE DEALERSHIP");

        String name = readText(
                "Dealership name",
                "e.g. Central Motors",
                2,
                80
        );

        String address = readText(
                "Address",
                "e.g. Main Street 10, Prague",
                5,
                150
        );

        dealershipService.createDealership(name, address);

        System.out.println("\nDealership created successfully.");
    }

    private Dealership selectDealership() {
        List<Dealership> dealerships =
                dealershipService.getAllDealerships();

        if (dealerships.isEmpty()) {
            System.out.println("No dealerships available.");
            return null;
        }

        printHeader("SELECT DEALERSHIP");

        for (int i = 0; i < dealerships.size(); i++) {
            Dealership dealership = dealerships.get(i);

            System.out.printf(
                    "%d. %s (%s)%n",
                    i + 1,
                    dealership.getName(),
                    dealership.getAddress()
            );
        }

        System.out.println("0. Cancel");

        int choice = readInt(
                "Select dealership",
                0,
                dealerships.size()
        );

        if (choice == 0) {
            return null;
        }

        return dealerships.get(choice - 1);
    }

    private void vehicleMenu() {
        boolean back = false;

        while (!back) {
            printHeader("VEHICLES");

            System.out.println("1. List all vehicles");
            System.out.println("2. List cars");
            System.out.println("3. List motorbikes");
            System.out.println("4. Search vehicles");
            System.out.println("5. Add car");
            System.out.println("6. Add motorbike");
            System.out.println("7. Change offer status");
            System.out.println("8. Delete vehicle");
            System.out.println("0. Back");

            int choice = readInt("Select option", 0, 8);

            switch (choice) {
                case 1 -> displayTransports(transportService.findAll());
                case 2 -> displayTransports(carService.findAllCars());
                case 3 -> displayTransports(motorbikeService.findAllMotorbikes());
                case 4 -> searchMenu();
                case 5 -> addCar();
                case 6 -> addMotorbike();
                case 7 -> changeOfferStatus();
                case 8 -> deleteVehicle();
                case 0 -> back = true;
            }

            if (!back) {
                pause();
            }
        }
    }

    private void searchMenu() {
        boolean back = false;

        while (!back) {
            printHeader("SEARCH VEHICLES");

            System.out.println("1. Search by VIN");
            System.out.println("2. Search by SPZ");
            System.out.println("3. Search by brand");
            System.out.println("4. Search by model");
            System.out.println("5. Search by brand and model");
            System.out.println("6. Search by fuel type");
            System.out.println("7. Search by offer status");
            System.out.println("0. Back");

            int choice = readInt("Select search method", 0, 7);

            switch (choice) {
                case 1 -> {
                    String vin = readText(
                            "VIN",
                            "e.g. WVWZZZ1JZXW000001",
                            17,
                            17
                    );

                    Transport result = transportService.findByVIN(vin);

                    if (result == null) {
                        System.out.println("No vehicle found.");
                    } else {
                        displayTransports(List.of(result));
                    }
                }

                case 2 -> {
                    String spz = readText(
                            "SPZ",
                            "e.g. 1AB1234",
                            1,
                            15
                    );

                    Transport result = transportService.findBySPZ(spz);

                    if (result == null) {
                        System.out.println("No vehicle found.");
                    } else {
                        displayTransports(List.of(result));
                    }
                }

                case 3 -> {
                    String brand = readText(
                            "Brand",
                            "e.g. BMW",
                            1,
                            50
                    );

                    displayTransports(
                            transportService.findByBrand(brand)
                    );
                }

                case 4 -> {
                    String model = readText(
                            "Model",
                            "e.g. Golf",
                            1,
                            50
                    );

                    displayTransports(
                            transportService.findByModel(model)
                    );
                }

                case 5 -> {
                    String brand = readText(
                            "Brand",
                            "e.g. BMW",
                            1,
                            50
                    );

                    String model = readText(
                            "Model",
                            "e.g. X5",
                            1,
                            50
                    );

                    displayTransports(
                            transportService.findByBrandAndModel(brand, model)
                    );
                }

                case 6 -> {
                    FuelType fuelType =
                            readEnum(FuelType.class, "Fuel type");

                    displayTransports(
                            transportService.findByFuelType(fuelType)
                    );
                }

                case 7 -> {
                    OfferStatus status =
                            readEnum(OfferStatus.class, "Offer status");

                    displayTransports(
                            transportService.findByOfferStatus(status)
                    );
                }

                case 0 -> back = true;
            }

            if (!back) {
                pause();
            }
        }
    }

    private void addCar() {
        Dealership dealership = selectDealership();

        if (dealership == null) {
            return;
        }

        printHeader("ADD CAR");

        String vin = readText(
                "VIN",
                "e.g. WVWZZZ1JZXW000001",
                17,
                17
        );

        if (transportService.findByVIN(vin) != null) {
            System.out.println("A vehicle with this VIN already exists.");
            return;
        }

        String spz = readText(
                "SPZ",
                "e.g. 1AB1234",
                1,
                15
        );

        if (transportService.findBySPZ(spz) != null) {
            System.out.println("A vehicle with this SPZ already exists.");
            return;
        }

        String brand = readText("Brand", "e.g. Volkswagen", 1, 50);
        String model = readText("Model", "e.g. Golf", 1, 50);

        double consumption = readDouble(
                "Average consumption",
                "e.g. 5.8",
                0
        );

        Year year = readYear();

        BigDecimal mileage = readBigDecimal(
                "Mileage (km)",
                "e.g. 65000",
                BigDecimal.ZERO
        );

        FuelType fuelType = readEnum(FuelType.class, "Fuel type");

        BigDecimal price = readBigDecimal(
                "Price (CZK)",
                "e.g. 429000",
                BigDecimal.ZERO
        );

        CarBodyType bodyType =
                readEnum(CarBodyType.class, "Body type");

        String gearbox = readText(
                "Gearbox",
                "e.g. Manual / Automatic",
                2,
                30
        );

        int doors = readInt("Number of doors", 2, 6);
        int seats = readInt("Number of seats", 1, 20);

        double engineCapacity = readDouble(
                "Engine capacity (cm³)",
                "e.g. 1498",
                0
        );

        int enginePower = readInt("Engine power (kW)", 1, 2000);

        Car car = carService.createCar(
                vin,
                spz,
                brand,
                model,
                consumption,
                year,
                mileage,
                fuelType,
                price,
                bodyType,
                gearbox,
                doors,
                seats,
                engineCapacity,
                enginePower
        );

        dealership.addTransport(car);

        System.out.println(
                "\nCar successfully created and assigned to "
                        + dealership.getName() + "."
        );
    }

    private void addMotorbike() {
        Dealership dealership = selectDealership();

        if (dealership == null) {
            return;
        }

        printHeader("ADD MOTORBIKE");

        String vin = readText(
                "VIN",
                "e.g. JH2SC5900MK000007",
                17,
                17
        );

        if (transportService.findByVIN(vin) != null) {
            System.out.println("A vehicle with this VIN already exists.");
            return;
        }

        String spz = readText(
                "SPZ",
                "e.g. 1AB1234",
                1,
                15
        );

        if (transportService.findBySPZ(spz) != null) {
            System.out.println("A vehicle with this SPZ already exists.");
            return;
        }

        String brand = readText("Brand", "e.g. Honda", 1, 50);
        String model = readText("Model", "e.g. CB650R", 1, 50);

        double consumption = readDouble(
                "Average consumption",
                "e.g. 4.9",
                0
        );

        Year year = readYear();

        BigDecimal mileage = readBigDecimal(
                "Mileage (km)",
                "e.g. 12000",
                BigDecimal.ZERO
        );

        FuelType fuelType = readEnum(FuelType.class, "Fuel type");

        String gearbox = readText(
                "Gearbox",
                "e.g. Manual",
                2,
                30
        );

        double engineCapacity = readDouble(
                "Engine capacity (cm³)",
                "e.g. 649",
                0
        );

        int enginePower = readInt("Engine power (kW)", 1, 2000);
        int seats = readInt("Number of seats", 1, 3);

        BigDecimal price = readBigDecimal(
                "Price (CZK)",
                "e.g. 189000",
                BigDecimal.ZERO
        );

        MotorbikeType motorbikeType =
                readEnum(MotorbikeType.class, "Motorbike type");

        Motorbike motorbike = motorbikeService.createMotorbike(
                vin,
                spz,
                brand,
                model,
                consumption,
                year,
                mileage,
                fuelType,
                gearbox,
                engineCapacity,
                enginePower,
                seats,
                price,
                motorbikeType
        );

        dealership.addTransport(motorbike);

        System.out.println(
                "\nMotorbike successfully created and assigned to "
                        + dealership.getName() + "."
        );
    }

    private void changeOfferStatus() {
        printHeader("CHANGE OFFER STATUS");

        displayTransports(transportService.findAll());

        String idInput = readText(
                "Vehicle UUID",
                "Copy UUID from the vehicle list",
                36,
                36
        );

        UUID id;

        try {
            id = UUID.fromString(idInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format.");
            return;
        }

        Transport transport = transportService.findById(id);

        if (transport == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println(
                "Current status: " + transport.getOfferStatus()
        );

        OfferStatus newStatus =
                readEnum(OfferStatus.class, "New offer status");

        transportService.updateOfferStatus(id, newStatus);

        System.out.println("Offer status successfully updated.");
    }

    private void deleteVehicle() {
        printHeader("DELETE VEHICLE");

        List<Transport> transports = transportService.findAll();

        if (transports.isEmpty()) {
            System.out.println("There are no vehicles to delete.");
            return;
        }

        displayTransports(transports);

        String idInput = readText(
                "Vehicle UUID",
                "Copy UUID from the vehicle list",
                36,
                36
        );

        UUID id;

        try {
            id = UUID.fromString(idInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format.");
            return;
        }

        Transport transport = transportService.findById(id);

        if (transport == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.printf(
                "%nSelected vehicle: %s %s%n",
                transport.getBrand(),
                transport.getModel()
        );

        String confirmation = readConfirmation(
                "Are you sure you want to delete this vehicle?"
        );

        if (!confirmation.equals("Y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        transportService.delete(id);

        dealershipService.removeTransportFromAllDealerships(id);

        System.out.println("Vehicle successfully deleted.");
    }

    private void displayTransports(List<? extends Transport> transports) {
        if (transports == null || transports.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            return;
        }

        for (int i = 0; i < transports.size(); i++) {
            Transport transport = transports.get(i);

            System.out.printf(
                    "%n%d. %s %s (%s)%n",
                    i + 1,
                    transport.getBrand(),
                    transport.getModel(),
                    transport.getYearOfManufacture()
            );

            System.out.println("   ID: " + transport.getId());
            System.out.println("   Type: " + transport.getTransportType());
            System.out.println("   VIN: " + transport.getVIN());
            System.out.println("   SPZ: " + transport.getSPZ());
            System.out.println("   Fuel: " + transport.getFuelType());
            System.out.println("   Mileage: " + transport.getMileage() + " km");
            System.out.println("   Price: " + transport.getPrice() + " CZK");
            System.out.println("   Status: " + transport.getOfferStatus());

            if (transport instanceof Car car) {
                System.out.println("   Body type: " + car.getBodyType());
                System.out.println("   Doors: " + car.getNumberOfDoors());
                System.out.println("   Seats: " + car.getNumberOfSeats());
            }

            if (transport instanceof Motorbike motorbike) {
                System.out.println(
                        "   Motorbike type: " + motorbike.getMotorbikeType()
                );

                System.out.println(
                        "   Seats: " + motorbike.getNumberOfSeats()
                );
            }
        }
    }

    private String readText(
            String label,
            String example,
            int minLength,
            int maxLength
    ) {
        while (true) {
            System.out.printf(
                    "%s (%s, %d-%d characters): ",
                    label,
                    example,
                    minLength,
                    maxLength
            );

            String input = scanner.nextLine().trim();

            if (input.length() < minLength ||
                    input.length() > maxLength) {

                System.out.println(
                        "Invalid length. Expected "
                                + minLength + "-" + maxLength
                                + " characters."
                );

                continue;
            }

            return input;
        }
    }

    private int readInt(String label, int min, int max) {
        while (true) {
            System.out.printf("%s (%d-%d): ", label, min, max);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value < min || value > max) {
                    System.out.printf(
                            "Value must be between %d and %d.%n",
                            min,
                            max
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(
            String label,
            String example,
            double min
    ) {
        while (true) {
            System.out.printf("%s (%s): ", label, example);

            try {
                double value = Double.parseDouble(
                        scanner.nextLine().trim().replace(',', '.')
                );

                if (!Double.isFinite(value) || value < min) {
                    System.out.println(
                            "Value must be at least " + min + "."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private BigDecimal readBigDecimal(
            String label,
            String example,
            BigDecimal min
    ) {
        while (true) {
            System.out.printf("%s (%s): ", label, example);

            try {
                BigDecimal value = new BigDecimal(
                        scanner.nextLine().trim().replace(',', '.')
                );

                if (value.compareTo(min) < 0) {
                    System.out.println(
                            "Value must be at least " + min + "."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    private Year readYear() {
        int currentYear = Year.now().getValue();

        int year = readInt(
                "Year of manufacture",
                1886,
                currentYear
        );

        return Year.of(year);
    }

    private <E extends Enum<E>> E readEnum(
            Class<E> enumClass,
            String label
    ) {
        E[] values = enumClass.getEnumConstants();

        System.out.println("\n" + label + ":");

        for (int i = 0; i < values.length; i++) {
            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    values[i]
            );
        }

        int choice = readInt(
                "Select option",
                1,
                values.length
        );

        return values[choice - 1];
    }

    private String readConfirmation(String label) {
        while (true) {
            System.out.print(label + " (Y/N): ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return "Y";
            }

            if (input.equalsIgnoreCase("N")) {
                return "N";
            }

            System.out.println("Please enter Y or N.");
        }
    }

    private void printHeader(String title) {
        System.out.println("\n========================================");
        System.out.println("       " + title);
        System.out.println("========================================\n");
    }

    private void pause() {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }
}
