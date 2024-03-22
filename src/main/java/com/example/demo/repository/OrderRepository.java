package com.example.demo.repository;
import com.example.demo.entity.Order;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
    Integer countByIsSettledAndUser(boolean isSettled, User user);
    Integer countByIsSettled(boolean isSettled);
    Integer countByUser(User user);

    @Query("SELECT SUM(o.revenue) FROM Order o WHERE o.user=?1")
    Double getTotalRevenueOfUser(User user);
    @Query("SELECT SUM(o.income) FROM Order o WHERE o.user =?1")
    Double getTotalIncomeOfUser(User user);
    @Query("SELECT SUM(o.totalPurchaseValue) FROM Order o WHERE o.user =?1")
    Double getTotalPurchaseValueOfUser(User user);

    @Query("SELECT SUM(o.revenue) FROM Order o")
    Double getTotalRevenue();
    @Query("SELECT SUM(o.income) FROM Order o")
    Double getTotalIncome();
    @Query("SELECT SUM(o.totalPurchaseValue) FROM Order o")
    Double getTotalPurchaseValue();


}
