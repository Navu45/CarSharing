package ru.navu.carsharing.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.navu.carsharing.models.Car;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.services.order.BookingService;
import ru.navu.carsharing.services.order.BookingServiceImpl;

@Controller
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
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
            @RequestParam Car car) {
        service.save(8.99, user, car);
        return "redirect:/bookings";
    }

    @PostMapping("/booking/close/{id}")
    public String closeTheBooking(@PathVariable Long id) {
        service.finish(id);
        return "redirect:/bookings";
    }

}
