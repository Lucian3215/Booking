package com.example.booking.service;

import com.example.booking.model.User;
import com.example.booking.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(String token) {
        User newUser = new User(token);
        return this.userRepo.save(newUser);
    }
}
