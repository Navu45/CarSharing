package ru.navu.carsharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.navu.carsharing.repositories.UserRepository;

@Controller
public class UserController {
    UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
