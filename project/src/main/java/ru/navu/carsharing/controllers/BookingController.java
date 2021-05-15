package ru.navu.carsharing.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.services.order.BookingServiceImpl;

@Controller
public class BookingController {

    private final BookingServiceImpl service;

    public BookingController(BookingServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/bookings")
    public String getAllBookings(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("bookings", service.getAllBookings(user));
        return "bookings";
    }

    @PostMapping("/booking/create")
    public String createBooking(
            @AuthenticationPrincipal User user,
            @RequestParam double serviceCost) {
        service.save(serviceCost, user);
        return "redirect:/bookings";
    }

    @PostMapping("/booking/close/{id}")
    public String closeTheBooking(@PathVariable Long id) {
        service.finish(id);
        return "redirect:/bookings";
    }

}
