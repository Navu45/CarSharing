package ru.navu.carsharing.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.navu.carsharing.models.Role;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.repositories.UserRepository;
import ru.navu.carsharing.services.security.user.UserService;
import ru.navu.carsharing.services.security.user.UserValidator;
import ru.navu.carsharing.services.security.SecurityService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthenticationController {
    UserRepository repository;
    UserValidator validator;
    UserService userService;
    SecurityService securityService;

    public AuthenticationController(UserRepository repository, UserValidator validator,
                                    UserService userService, SecurityService securityService) {
        this.repository = repository;
        this.validator = validator;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        validator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "registration";
        }
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(Role.USER);
        userForm.setRoles(rolesSet);
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user != null)
            return "redirect:/home";

        if (error != null)
            model.addAttribute("error", "Username or/and password is incorrect.");

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }
}
