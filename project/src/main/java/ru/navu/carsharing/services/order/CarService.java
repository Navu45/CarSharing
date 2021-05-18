package ru.navu.carsharing.services.order;

import org.springframework.stereotype.Service;
import ru.navu.carsharing.models.Booking;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {

    CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getNearbyCars(double lat, double lng) {
        return repository.findCarsByLatitudeBetweenAndLongitudeBetween(
                lat - 0.007, lat + 0.007, lng - 0.007, lng + 0.007
        );
    }

    public void save(Car car) {
        repository.save(car);
    }

    public void addUserToCar(Booking booking, Car car) {
        car.getRecentUsers().add(booking);
    }

    public void init(double lat, double lng) {
        for (int i = 0; i < 7; i++) {
            Car car = new Car();
            car.setCarNumber("A111BC197");
            double lat_p = Math.random() * 0.006 * Math.pow(-1.0, Math.round(Math.random() * 10));
            double lng_p = Math.random() * 0.006 * Math.pow(-1.0, Math.round(Math.random() * 10));
            car.setLatitude(lat + lat_p);
            car.setLongitude(lng + lng_p);
            car.setFuel(80);
            save(car);
        }
    }

    public Car getCar(Long id) {
        return repository.getOne(id);
    }
}
