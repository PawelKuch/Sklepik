package com.example.demo.repository;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(total_purchase_value) FROM expenses e WHERE e.user =?1")
    Double getTotalPurchaseValueOfUser(User user);
    @Query("SELECT SUM(total_purchase_value) FROM expenses)")
    Double getTotalPurchaseValue();
}
