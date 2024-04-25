package com.example.demo.repository;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.statistics.UserExpensesStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(e.totalExpenseValue) FROM Expense e WHERE e.user =?1")
    Optional<Double> getTotalPurchaseValueOfUser(User user);
    @Query("SELECT SUM(e.totalExpenseValue) FROM Expense e")
    Optional<Double> getTotalPurchaseValue();

    @Query("SELECT new com.example.demo.statistics.UserExpensesStatistics(e.user.userId, e.user.name, SUM(e.totalExpenseValue), COUNT(e)) " +
            "FROM Expense e " +
            "JOIN User u ON e.user = u " +
            "GROUP BY u")
    List<UserExpensesStatistics> getUserExpensesStatistics();
}
