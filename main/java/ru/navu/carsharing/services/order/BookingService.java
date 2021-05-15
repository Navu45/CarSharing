package ru.navu.carsharing.services.order;

import ru.navu.carsharing.models.User;

public interface BookingService {
    void save(double serviceCost, User user);
    void finish(Long id);

}
