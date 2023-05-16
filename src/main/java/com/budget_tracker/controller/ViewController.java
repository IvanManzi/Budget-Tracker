package com.budget_tracker.controller;


import com.budget_tracker.model.Budget;
import com.budget_tracker.model.Expense;
import com.budget_tracker.model.Income;
import com.budget_tracker.model.User;
import com.budget_tracker.service.BudgetManagerService;
import com.budget_tracker.service.UserManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping(value="/view")
@RequiredArgsConstructor
public class ViewController {

    private final BudgetManagerService budgetManagerService;

    private final UserManagerService userManagerService;

    @GetMapping(value = "/income")
    public String getIncomePage(Model model) {
        User user = userManagerService.getCurrentUser();
        System.out.println("USERNAME "+user.getUsername());
        Income incomeObj = new Income();
        List<Income> income = budgetManagerService.getAllIncome(user.getUserId());
        model.addAttribute("income", income);
        model.addAttribute("incomeObj", incomeObj);
        return "income";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/signup")
    public String getSignup() {
        return "signup";
    }

    @GetMapping(value = "/about")
    public String getAboutView() {
        return "about";
    }

    @GetMapping(value = "/home")
    public String getHomeView() {
        return "home";
    }

    @GetMapping(value = "/how-it-works")
    public String getHowItWorksView() {
        return "how-it-works";
    }

    @GetMapping(value = "/login")
    public String getLoginView() {
        return "login";
    }


    @GetMapping(value = "/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping(value = "/expenses")
    public String getExpensesPage(Model model) {
        User savedUser = userManagerService.getCurrentUser();
        Expense expenseObj = new Expense();
        List<Expense> expense = budgetManagerService.getAllExpenses(savedUser.getUserId());
        model.addAttribute("expenseObj",expenseObj);
        model.addAttribute("expense",expense);
        return "expenses";
    }

    @GetMapping(value = "/budget")
    public String getBudget(Model model) {
        User savedUser = userManagerService.getCurrentUser();
        Budget budgetObj = new Budget();
        List<Budget> budget = budgetManagerService.getAllBudget(savedUser.getUserId());
        model.addAttribute("budgetObj",budgetObj);
        model.addAttribute("budget",budget);
        return "budget";
    }


    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        User savedUser = userManagerService.getCurrentUser();
        model.addAttribute("user",savedUser);
        return "profile";
    }


}
