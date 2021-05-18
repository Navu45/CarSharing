package ru.navu.carsharing.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.services.order.BookingService;
import ru.navu.carsharing.services.order.BookingServiceImpl;
import ru.navu.carsharing.services.order.CarService;

@RestController
public class BookingController {

    private final BookingServiceImpl bookingService;
    private final CarService carService;

    public BookingController(BookingServiceImpl bookingService, CarService carService) {
        this.bookingService = bookingService;
        this.carService = carService;
    }



    @PostMapping("/booking/create/{car_id}")
    public void createBooking(
            @AuthenticationPrincipal User user,
            @PathVariable Long car_id) {
        bookingService.save(8.99, user, carService.getCar(car_id));
    }

    @PostMapping("/booking/close_c/{car_id}")
    public void closeTheBookingWithCarID(@PathVariable Long car_id) {
        bookingService.finishWithCar(carService.getCar(car_id));
    }


}
