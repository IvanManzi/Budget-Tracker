package com.vehicle_management.service.impl;

import com.vehicle_management.model.User;
import com.vehicle_management.model.Vehicle;
import com.vehicle_management.repository.UserRepo;
import com.vehicle_management.repository.VehicleRepository;
import com.vehicle_management.service.VehicleService;
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
