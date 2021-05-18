package ru.navu.carsharing.services.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.navu.carsharing.models.Booking;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.repositories.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{

    private BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(double serviceCost, User user, Car car) {
        Booking booking = new Booking();
        booking.setServiceCost(serviceCost);
        booking.setStartTime(LocalDateTime.now());
        booking.setUser(user);
        booking.setCarNumber(car.getCarNumber());
        repository.save(booking);
    }

    @Override
    public void finish(Long id) {
        Booking booking = repository.getOne(id);
        if (!booking.isFinished()) {
            booking.setFinished(true);
            booking.setFinishTime(LocalDateTime.now());
            repository.save(booking);
        }
    }

    @Override
    public List<Booking> getAllBookings(User user) {
        return repository.findByUser(user);
    }
}
