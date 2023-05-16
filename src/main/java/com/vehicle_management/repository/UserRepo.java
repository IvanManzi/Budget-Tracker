package com.vehicle_management.repository;

import com.vehicle_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User getUserByPhoneNumber(String phoneNumber);
    User findByUsername(String username);

//    User getUserByEmail(String email);
}
