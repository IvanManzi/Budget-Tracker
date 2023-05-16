package com.budget_tracker.service.impl;

import com.budget_tracker.model.User;
import com.budget_tracker.model.Vehicle;
import com.budget_tracker.repository.UserRepo;
import com.budget_tracker.repository.VehicleRepository;
import com.budget_tracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserRepo userRepo;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> getAll(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.get().getVehicles();
    }

    @Override
    public Optional<Vehicle> findVehicleById(int vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }


}
