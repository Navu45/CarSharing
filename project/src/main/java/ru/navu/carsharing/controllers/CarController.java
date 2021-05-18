package ru.navu.carsharing.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.services.order.CarService;

import java.util.List;


@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/close_to_user/{lat}/{lng}")
    public List<Car> getNearbyCars(@PathVariable double lat,
                                   @PathVariable double lng) {
        return carService.getNearbyCars(lat, lng);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("init/{lat}/{lng}")
    public void init(@PathVariable double lat,
                     @PathVariable double lng) {
        for (int i = 0; i < 7; i++) {
            Car car = new Car();
            car.setCarNumber("A111BC197");
            car.setLatitude(lat + Math.random() * 0.006 * Math.pow(-1.0, Math.round(Math.random() * 10)));
            car.setLongitude(lng + Math.random() * 0.006 * Math.pow(-1.0, Math.round(Math.random() * 10)));
            carService.save(car);
        }
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("save")
    public void save(@RequestParam Car car) {
        carService.save(car);
    }
}
