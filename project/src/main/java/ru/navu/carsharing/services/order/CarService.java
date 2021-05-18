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
                lat - 1, lat + 1, lng - 1, lng + 1
        );
    }

    public void save(Car car) {
        repository.save(car);
    }

    public void addUserToCar(Booking booking, Car car) {
        car.getRecentUsers().add(booking);
    }
}
