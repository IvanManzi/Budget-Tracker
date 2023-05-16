package com.budget_tracker.service;

import com.budget_tracker.model.User;

import java.io.IOException;
import java.util.Optional;

public interface UserManagerService {

    User createUser(User user);
    Optional<User> getUserById(Long userId);
    User getCurrentUser();
    String authenticateUser(User user);
    void sendOtp(String phoneNumber) throws IOException;
    User updateUser(User user);
}
