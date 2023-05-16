package com.budget_tracker.repository;

import com.budget_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income,Long> {
}
