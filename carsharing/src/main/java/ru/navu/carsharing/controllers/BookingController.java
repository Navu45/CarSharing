package ru.navu.carsharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.navu.carsharing.models.Booking;
import ru.navu.carsharing.repositories.BookingRepository;

import java.time.LocalDateTime;

@Controller
public class BookingController {

    private BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/bookings")
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "booking";
    }

    @PostMapping("/booking/create")
    public String createBooking(@RequestParam double serviceCost) {
        Booking booking = new Booking();
        booking.setServiceCost(serviceCost);
        booking.setStartTime(LocalDateTime.now());
        bookingRepository.save(booking);

        return "redirect:/bookings";
    }

    @PostMapping("/booking/close/{id}")
    public String closeTheBooking(@PathVariable Long id) {
        Booking booking = bookingRepository.getOne(id);
        if (booking.isFinished())
            return "redirect:/bookings";
        booking.setFinished(true);
        booking.setFinishTime(LocalDateTime.now());
        bookingRepository.save(booking);
        return "redirect:/bookings";
    }

}
