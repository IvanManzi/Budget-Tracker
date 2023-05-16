package com.budget_tracker.repository;

import com.budget_tracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepo extends JpaRepository<Budget,Long> {
}
