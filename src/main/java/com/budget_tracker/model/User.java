package com.budget_tracker.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String username;

    private Integer otp;

    private String country;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Income> incomeList = new ArrayList<>();

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate updatedAt;



}
