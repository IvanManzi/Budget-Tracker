package com.budget_tracker.service.impl;


import com.budget_tracker.Util.SendRequestUtil;
import com.budget_tracker.Util.VerifyCodeUtil;
import com.budget_tracker.model.CustomUserDetails;
import com.budget_tracker.model.User;
import com.budget_tracker.repository.UserRepo;
import com.budget_tracker.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserManagerServiceImpl implements UserManagerService, UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside load");
        User user = userRepo.findByUsername(username);
        System.out.println("username "+username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new CustomUserDetails(user);
    }

    @Override
    public User getCurrentUser() {
        // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // Check if the principal is a UserDetails object
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // Find the user in the database based on the username
                return userRepo.findByUsername(userDetails.getUsername());
            }
        }

        return null;
    }

    @Override
    public User createUser(User user) {
        //check if phone number exists before saving
        User check = userRepo.getUserByPhoneNumber(user.getPhoneNumber());
        if(check != null){
            return null;
        }
        Integer otp = Integer.valueOf(VerifyCodeUtil.generateVerifyCode(4,"0123456789"));
        user.setOtp(otp);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

//    @Override
//    public User toSendEmail(User user) {
//        User getuser = userRepo.getUserByEmail(user.getEmail());
//        return getuser;
//    }

    @Override
    public String authenticateUser(User user) {
        User savedUser = userRepo.getUserByPhoneNumber(user.getPhoneNumber());
        if(savedUser == null){
            return null;
        }
        if(savedUser.getOtp().equals(user.getOtp())){
            return String.valueOf(savedUser.getUserId());
        }
        return null;
    }

//    @Override
//    public boolean authenticateUserByEmail(User user) {
//        String requestMail="";
//        String requestPassword="";
//        User userEmail = userRepo.getUserByEmail(user.getEmail());
//        User userPassword = userRepo.getUserByEmail(user.getEmail());
//        if(userEmail.equals(requestMail) && userPassword.equals(requestPassword)) {
//            return true;
//        }else {
//            return false;
//        }
//    }


    @Override
    public void sendOtp(String phoneNumber) throws IOException {
        User savedUser = userRepo.getUserByPhoneNumber(phoneNumber);
        String otp = VerifyCodeUtil.generateVerifyCode(4,"0123456789");
        savedUser.setOtp(Integer.valueOf(otp));
        SendRequestUtil.sendPhoneNumberVerificationCode(phoneNumber,otp);
        userRepo.save(savedUser);
    }

    @Override
    public User updateUser(User user) {
        User savedUser = userRepo.findByUsername(user.getUsername());
        if (savedUser != null) {
            savedUser.setFirstName(user.getFirstName());
            savedUser.setLastName(user.getLastName());
            savedUser.setAddress(user.getAddress());
            savedUser.setCountry(user.getCountry());
            savedUser.setPassword(user.getPassword());
            savedUser.setPhoneNumber(user.getPhoneNumber());
            return userRepo.save(savedUser);
        }
        return null;
    }
}
