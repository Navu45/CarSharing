package ru.navu.carsharing.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.services.order.BookingService;
import ru.navu.carsharing.services.order.BookingServiceImpl;

@Controller
public class TripsController {

    BookingServiceImpl service;

    public TripsController(BookingServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/bookings")
    public String getAllBookings(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("bookings", service.getAllBookings(user));
        return "bookings";
    }

    @PostMapping("/booking/close_b/{booking_id}")
    public String closeTheBookingWithBookingID(@PathVariable Long booking_id) {
        service.finishWithBooking(booking_id);
        return "redirect:/bookings";
    }
}
