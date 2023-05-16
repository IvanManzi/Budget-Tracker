package com.vehicle_management.repository;

import com.vehicle_management.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income,Long> {
}
