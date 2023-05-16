package com.vehicle_management.controller;

import com.vehicle_management.model.*;
import com.vehicle_management.service.BudgetManagerService;
import com.vehicle_management.service.UserManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/budget/")
@RequiredArgsConstructor
public class BudgetManagerController {


    private final BudgetManagerService budgetManagerService;
    private final UserManagerService userManagerService;


    @PostMapping(value = "/create")
    public String createBudget(@ModelAttribute("budget") Budget budget, HttpSession session) {
        User user = userManagerService.getCurrentUser();
        budget.setUser(user);
        Budget savedBudget = budgetManagerService.addBudget(budget);
        if (savedBudget != null) {
            return "redirect:/view/budget";
        }
        return "error-page";

    }

    @PostMapping(value = "delete/")
    public String deleteBudget(@RequestParam("budgetId") Long budgetId) {
        budgetManagerService.deleteBudget(budgetId);
        return "redirect:/view/budget";
    }

    @PostMapping(value = "/expense/create")
    public String createExpense(@ModelAttribute("expense") Expense expense) {
        User user = userManagerService.getCurrentUser();
        expense.setUser(user);
        Expense savedExpense = budgetManagerService.createExpense(expense);
        if (savedExpense != null) {
            return "redirect:/view/expenses";
        }
        return "error-page";

    }


    @PostMapping(value = "expense/delete/")
    public String deleteExpense(@RequestParam("expenseId") Long expenseId) {
        budgetManagerService.deleteExpense(expenseId);
        return "redirect:/view/expenses";
    }

    @PostMapping(value = "income/create")
    public String createIncome(@ModelAttribute("income") Income income) {
        User user = userManagerService.getCurrentUser();
        System.out.println("USER "+user.getUsername());
        income.setUser(user);
        Income savedIncome = budgetManagerService.addIncome(income);
        if (savedIncome != null) {
            System.out.println("success");
            return "redirect:/view/income";
        }
        System.out.println("fail");
        return "error-page";
    }

    @PostMapping(value = "income/delete/")
    public String deleteIncome(@RequestParam("ticketId") Long ticketId) {
        budgetManagerService.deleteIncome(ticketId);
        return "redirect:/view/income";
    }







}
