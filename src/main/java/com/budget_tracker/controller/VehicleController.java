package com.budget_tracker.controller;

import com.budget_tracker.model.User;
import com.budget_tracker.model.Vehicle;
import com.budget_tracker.repository.VehicleRepository;
import com.budget_tracker.service.UserManagerService;
import com.budget_tracker.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/vehicle")
public class VehicleController {

    @Autowired
    VehicleServiceImpl studentService;

    @Autowired
    UserManagerService userManagerService;
    private VehicleRepository vehicleRepository;

    @GetMapping(value = "/home")
    public String homePage() {
        return "home-page";
    }

    @GetMapping(value = "/registration")
    public String registerVehicle(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "registration-page";
    }

    @GetMapping(value = "/view")
    public String home(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "home-page";
        }
        List<Vehicle> vehicles = studentService.getAll(Long.valueOf(userId));
        model.addAttribute("vehicle", vehicles);
        System.out.println("attr "+session.getAttribute("userId"));
        return "home-page";
    }
    @PostMapping(value = "/register")
    public String createVehicle(@ModelAttribute("vehicle") Vehicle theVehicle,HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "error-page";
        }
        Optional<User> user = userManagerService.getUserById(Long.valueOf(userId));
        theVehicle.setUser(user.get());
        Vehicle savedVehicle = studentService.createVehicle(theVehicle);
        if (savedVehicle != null) {
            return "redirect:/vehicle/home";
        }
        return "error-page";
    }

    @GetMapping(value = "/updateForm")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        Optional<Vehicle> theStudent = studentService.findVehicleById(id);
        theModel.addAttribute("student", theStudent);
        return "edit";
    }
    @GetMapping(value = "/deleteForm")
    public String showFormForDelete(@RequestParam("id") int id, Model theModel) {
        Optional<Vehicle> vehicle = studentService.findVehicleById(id);
        theModel.addAttribute("vehicle", vehicle);
        return "delete";
    }


    @PostMapping(value = "/deleteVehicle")
    public String deleteStudent(@RequestParam("id") int id) {
        studentService.deleteVehicle(id);
        return "redirect:/vehicle/home";
    }

}
