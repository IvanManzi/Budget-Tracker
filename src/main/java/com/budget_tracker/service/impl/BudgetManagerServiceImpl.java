package com.budget_tracker.service.impl;

import com.budget_tracker.model.Budget;
import com.budget_tracker.model.Expense;
import com.budget_tracker.model.Income;
import com.budget_tracker.model.User;
import com.budget_tracker.repository.BudgetRepo;
import com.budget_tracker.repository.ExpenseRepo;
import com.budget_tracker.repository.IncomeRepo;
import com.budget_tracker.repository.UserRepo;
import com.budget_tracker.service.BudgetManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetManagerServiceImpl implements BudgetManagerService {

    private final BudgetRepo budgetRepo;

    private final ExpenseRepo expenseRepo;

    private final IncomeRepo incomeRepo;

    private final UserRepo userRepo;


    @Override
    public Budget addBudget(Budget budget) {
        return budgetRepo.save(budget);
    }

    @Override
    public List<Budget> getUserBudgets(Long userId) {
        User savedUser = userRepo.findById(userId).orElse(null);
        if(savedUser == null){
            return null;
        }
        return savedUser.getBudgets();
    }

    @Override
    public void deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id).orElse(null);
        if(budget != null){
            budgetRepo.delete(budget);
        }
    }

    @Override
    public List<Budget> getAllBudget(Long userId) {
        User savedUser = userRepo.findById(userId).orElse(null);
        if(savedUser == null){
            return null;
        }
        return savedUser.getBudgets();
    }

    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(Long userId) {
        User savedUser = userRepo.findById(userId).orElse(null);
        if(savedUser == null){
            return null;
        }
        return savedUser.getExpenses();
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = expenseRepo.findById(id).orElse(null);
        if(expense != null){
            expenseRepo.delete(expense);
        }
    }

    @Override
    public Income addIncome(Income income) {
        return incomeRepo.save(income);
    }

    @Override
    public List<Income> getAllIncome(Long userId) {
        User savedUser = userRepo.findById(userId).orElse(null);
        if(savedUser == null){
            return null;
        }
        return savedUser.getIncomeList();
    }

    @Override
    public void deleteIncome(Long id) {
        Income income = incomeRepo.findById(id).orElse(null);
        if(income != null){
            incomeRepo.delete(income);
        }
    }
}
