package com.budget_tracker.controller;

import com.budget_tracker.model.User;
import com.budget_tracker.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserManagerController {

    @Autowired
    UserManagerService userManagerService;

//    @Autowired
//    private EmailService emailService;



    @GetMapping(value = "/main-page")
    public String loadMainPage() {
        return "dashboard";
    }

    @GetMapping(value = "/sign-up")
    public String loadSignUpPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up";
    }

    @PostMapping(value = "/user/create")
    public String registerUser(@ModelAttribute("user") User user) {
        User savedUser = userManagerService.createUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        return "dashboard";
    }

    @PostMapping(value = "/user/update")
    public String updateUser(@ModelAttribute("user") User user) {
        User savedUser = userManagerService.updateUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        return "profile";
    }

    @GetMapping(value = "/validate-phone")
    public String loadValidatePhoneNumberPage(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "validate-phone";
    }

    @GetMapping(value = "/email-login")
    public String loadEmailLoginPage(){
        return "email-login";
    }

//    @GetMapping(value = "/validate-phone")
//    public String toSendEmail(Model model){
//        User user = new User();
//        model.addAttribute("user",user);
//        String email = user.getEmail();
//        emailService.sendEmail(email);
//        return "validate-phone";
//    }

    @PostMapping(value = "/user/send-otp")
    public String sendOtp(@ModelAttribute("user") User user) throws IOException {
        userManagerService.sendOtp(user.getPhoneNumber());
        return "validate-otp";
    }

    @PostMapping(value = "/user/auth")
    public String authenticateUser(@ModelAttribute("user") User user, HttpSession session){
        String userId = userManagerService.authenticateUser(user);
        if(userId == null){
            return "validate-phone";
        }
        System.out.println("User ID: "+userId);
        session.setAttribute("userId",userId);
        return "home-page";
    }

//    public String authenticateUserByEmail(@ModelAttribute("user") User user, HttpSession session){
//        String userId = userManagerService.authenticateUser(user);
//        if(userId == null){
//            return "email-login";
//        }
//        System.out.println("User ID: "+userId);
//        session.setAttribute("userId",userId);
//        return "home-page";
//    }

    @GetMapping(value = "/error-page")
    public String getErrorPage() {
        return "error-page";
    }
}
