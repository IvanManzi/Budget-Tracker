package com.vehicle_management.repository;

import com.vehicle_management.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepo extends JpaRepository<Budget,Long> {
}
