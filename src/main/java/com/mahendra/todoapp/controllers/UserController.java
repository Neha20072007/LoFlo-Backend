package com.mahendra.todoapp.controllers;

import com.mahendra.todoapp.models.User;
import com.mahendra.todoapp.services.UserService;
import com.mahendra.todoapp.services.MailService;  // Import the MailService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;  // Inject MailService

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationRequest request) {
        // Register the user
        User registeredUser = userService.registerUser(request.getName(), request.getEmail(), request.getPassword());

        // After successful registration, send a welcome email
        if (registeredUser != null) {
            mailService.sendRegistrationEmail(registeredUser.getEmail(), registeredUser.getName());
        }

        return registeredUser;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserLoginRequest request) {
        return userService.authenticateUser(request.getEmail(), request.getPassword());
    }
}
