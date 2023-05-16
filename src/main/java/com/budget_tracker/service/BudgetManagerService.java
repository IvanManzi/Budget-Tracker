package com.budget_tracker.service;

import com.budget_tracker.model.Budget;
import com.budget_tracker.model.Expense;
import com.budget_tracker.model.Income;

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
