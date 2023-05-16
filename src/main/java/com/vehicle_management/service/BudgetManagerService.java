package com.vehicle_management.service;

import com.vehicle_management.model.Budget;
import com.vehicle_management.model.Expense;
import com.vehicle_management.model.Income;

import java.util.List;

public interface BudgetManagerService {

    Budget addBudget(Budget budget);

    List<Budget> getUserBudgets(Long userId);

    void deleteBudget(Long id);

    List<Budget> getAllBudget(Long userId);

    Expense createExpense(Expense expense);

    List<Expense> getAllExpenses(Long userId);

    void deleteExpense(Long id);

    Income addIncome(Income income);

    List<Income> getAllIncome(Long userId);

    void deleteIncome(Long id);

}
