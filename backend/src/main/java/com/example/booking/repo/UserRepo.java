package com.example.booking.repo;

import com.example.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    void deleteUserById(Long id);

    User findUserByToken(String token);
}
