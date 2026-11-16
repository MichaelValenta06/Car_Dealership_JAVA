import com.car_dealership.app.irepo.ICarRepository;
import com.car_dealership.app.irepo.IDealershipRepository;
import com.car_dealership.app.irepo.IMotorbikeRepository;
import com.car_dealership.app.irepo.ITransportRepository;
import com.car_dealership.app.serv.CarService;
import com.car_dealership.app.serv.DealershipService;
import com.car_dealership.app.serv.MotorbikeService;
import com.car_dealership.app.serv.TransportService;
import com.car_dealership.config.DataSeeder;
import com.car_dealership.infra.repo.CarRepository;
import com.car_dealership.infra.repo.DealershipRepository;
import com.car_dealership.infra.repo.MotorbikeRepository;
import com.car_dealership.infra.repo.TransportRepository;
import com.car_dealership.ui.ConsoleUI;

static void main(String[] args) {

    ITransportRepository transportRepository =
            new TransportRepository();

    ICarRepository carRepository =
            new CarRepository(transportRepository);

    IMotorbikeRepository motorbikeRepository =
            new MotorbikeRepository(transportRepository);

    IDealershipRepository dealershipRepository =
            new DealershipRepository();


    TransportService transportService =
            new TransportService(transportRepository);

    CarService carService =
            new CarService(carRepository);

    MotorbikeService motorbikeService =
            new MotorbikeService(motorbikeRepository);

    DealershipService dealershipService =
            new DealershipService(dealershipRepository);


    DataSeeder dataSeeder = new DataSeeder(
            carService,
            motorbikeService,
            dealershipService
    );

    dataSeeder.seed();

    ConsoleUI consoleUI = new ConsoleUI(
            carService,
            motorbikeService,
            transportService,
            dealershipService
    );

    consoleUI.start();
}

