package com.example.demo.repository;

import com.example.demo.data.ExpenseDataQuery;
import com.example.demo.entity.Order;
import com.example.demo.statistics.GeneralStatistics;
import com.example.demo.statistics.UserOrdersStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);

 /*   @Query(value = "SELECT new com.example.demo.statistics.UserStatistics (o.user.userId, u.name, SUM(o.totalPurchaseValue), " +
            "SUM((SELECT e.totalExpenseValue FROM Expense e WHERE e.user = o.user))," +
            "SUM(o.revenue), SUM(o.income), COUNT(o.orderId)) " +
            "FROM Order o " +
            "JOIN User u ON o.user = u " +
            "GROUP BY o.user")
    List<UserStatistics> getStatistics();*/

    @Query("SELECT new com.example.demo.statistics.UserOrdersStatistics(o.user.userId, o.user.name, SUM(o.totalPurchaseValue)," +
            "SUM(o.revenue) ,SUM(o.income), COUNT(o) )" +
            "FROM Order o " +
            "JOIN User u ON o.user = u " +
            "GROUP BY u")
    List<UserOrdersStatistics> getUserOrdersStatistics();

    @Query("SELECT new com.example.demo.statistics.GeneralStatistics(SUM(o.totalPurchaseValue), SUM(o.income), SUM(o.revenue), " +
            "COUNT(o), COUNT(o.user), COUNT(o.item)) " +
            "FROM Order o")
    GeneralStatistics getGeneralStatistics();

    @Query("SELECT new com.example.demo.data.ExpenseDataQuery(SUM(e.totalExpenseValue), COUNT(e.expenseId)) " +
            "FROM Expense e")
    ExpenseDataQuery getExpenseDataQuery();
}
