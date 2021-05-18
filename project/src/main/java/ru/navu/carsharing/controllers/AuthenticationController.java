package ru.navu.carsharing.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.navu.carsharing.models.User;
import ru.navu.carsharing.services.security.SecurityService;
import ru.navu.carsharing.services.user.UserService;
import ru.navu.carsharing.services.validation.ValidationUtil;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class AuthenticationController {
    UserService userService;
    SecurityService securityService;
    ValidationUtil validationUtil;



    public AuthenticationController(UserService userService,
                                    SecurityService securityService,
                                    ValidationUtil validationUtil) {
        this.userService = userService;
        this.securityService = securityService;
        this.validationUtil = validationUtil;
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
    public String registration(@Valid User userForm,
                               BindingResult bindingResult, Model model) throws MessagingException {
        boolean userExists = userService.findByUsername(userForm.getUsername()) != null;
        boolean inputInvalid = bindingResult.hasErrors();
        boolean notConfirmedPassword = userForm.getPassword() != null
                && !userForm.getPassword().equals(userForm.getConfirmPassword());

        if (userExists || inputInvalid || notConfirmedPassword)
        {
            if (inputInvalid)
            {
                model.mergeAttributes(validationUtil.getErrors(bindingResult));
            }

            if (userExists)
            {
                model.addAttribute("userExistsError", "User already exists with this email.");
            }

            if (notConfirmedPassword) {
                model.addAttribute("confirmationError", "Passwords are different.");
            }
            return "registration";
        }


        userService.save(userForm);
        model.addAttribute("message", "Activate your account. Activation code was sent to " + userForm.getUsername());

        return "login";
    }



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user != null ) {
            if (!user.isActive()){
                model.addAttribute("error", "Account is not activated." +
                        " Activation code was sent to" + user.getUsername());
                return "login";
            }
            return "redirect:/home";
        }

        if (error != null)
            model.addAttribute("error", "Username or/and password is incorrect.");

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @GetMapping("activate/{code}")
    public String activate(Model model,
                           @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
        }
        else {
            model.addAttribute("message", "Activation code is not found.");
        }
        return "login";
    }

}
