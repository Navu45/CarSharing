package ru.navu.carsharing.services.order;

import ru.navu.carsharing.models.Booking;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.models.User;

import java.util.List;

public interface BookingService {
    Booking save(double serviceCost, User user, Car car);

    List<Booking> getAllBookings(User user);
}
