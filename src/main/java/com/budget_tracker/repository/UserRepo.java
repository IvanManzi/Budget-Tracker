package com.budget_tracker.repository;

import com.budget_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User getUserByPhoneNumber(String phoneNumber);
    User findByUsername(String username);

//    User getUserByEmail(String email);
}
