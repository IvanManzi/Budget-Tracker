package com.budget_tracker.controller;


import com.budget_tracker.service.UserManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserManagerService userService;

    @GetMapping(value = "/login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/login/success")
    public String showLoginSuccess(Model model) {
        System.out.println("success login");
        model.addAttribute("message", "Authentication successful!");
        return "dashboard";
    }
}
