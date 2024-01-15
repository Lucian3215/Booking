package com.example.booking.controller;

import com.example.booking.model.User;
import com.example.booking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/user"))
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<User> addUser(@PathVariable String token) {
        return new ResponseEntity<>(userService.addUser(token), HttpStatus.CREATED);
    }

}
