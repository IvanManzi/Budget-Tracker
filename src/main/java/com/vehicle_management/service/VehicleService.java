package com.vehicle_management.service;

import com.vehicle_management.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
    List<Vehicle> getAll(Long userId);
    Optional<Vehicle> findVehicleById(int vehicleId);
}

