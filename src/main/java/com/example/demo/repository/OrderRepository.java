package com.example.demo.repository;
import com.example.demo.entity.Order;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
    Long countByIsSettledAndUser(boolean isSettled, User user);
    Long countByIsSettled(boolean isSettled);
    Long countByUser(User user);

    @Query("SELECT SUM(o.revenue) FROM Order o WHERE o.user=?1")
    Optional<Double> getTotalRevenueOfUser(User user);
    @Query("SELECT SUM(o.income) FROM Order o WHERE o.user =?1")
    Optional<Double> getTotalIncomeOfUser(User user);
    @Query("SELECT SUM(o.totalPurchaseValue) FROM Order o WHERE o.user =?1")
    Optional<Double> getTotalPurchaseValueOfUser(User user);

    @Query("SELECT SUM(o.revenue) FROM Order o")
    Optional<Double> getTotalRevenue();
    @Query("SELECT SUM(o.income) FROM Order o")
    Optional<Double> getTotalIncome();
    @Query("SELECT SUM(o.totalPurchaseValue) FROM Order o")
    Optional<Double> getTotalPurchaseValue();


}
