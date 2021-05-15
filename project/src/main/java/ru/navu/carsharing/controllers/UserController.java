package ru.navu.carsharing.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.navu.carsharing.models.Role;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.repositories.UserRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", repository.findAll());
        return "admin";
    }

    @GetMapping("/edit/{user}")
    public String editUser(Model model,
                           @PathVariable User user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "edit_user";
    }

    @PostMapping("/update")
    public String saveUser(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("user_id") User user) {
        user.setUsername(username);
        user.getRoles().clear();
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        repository.save(user);
        return "redirect:/user/list";
    }
}

