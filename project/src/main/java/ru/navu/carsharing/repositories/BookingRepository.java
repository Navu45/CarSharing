package ru.navu.carsharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.navu.carsharing.models.Booking;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findBookingByCarAndFinishedFalse(Car car);
}
