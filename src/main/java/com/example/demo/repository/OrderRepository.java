package com.example.demo.repository;
import com.example.demo.entity.Order;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
    Order findByUser(User user);
    Integer countByIsSettledAndUser(boolean isSettled, User user);
    Integer countByIsSettled(boolean isSettled);
    Integer countByUser(User user);

    @Query("SELECT SUM(revenue) FROM orders o WHERE o.user=?1")
    Double getTotalRevenueOfUser(User user);
    @Query("SELECT SUM(income) FROM orders o WHERE o.user =?1")
    Double getTotalIncomeOfUser(User user);
    @Query("SELECT SUM(total_purchase_value) FROM orders o WHERE o.user =?1")
    Double getTotalPurchaseValueOfUser(User user);

    @Query("SELECT SUM(revenue) FROM orders")
    Double getTotalRevenue();
    @Query("SELECT SUM(income) FROM orders")
    Double getTotalIncome();
    @Query("SELECT SUM(total_purchase_value) FROM orders")
    Double getTotalPurchaseValue();


}
