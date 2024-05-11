package com.example.demo.repository;

import com.example.demo.data.ExpenseDataQuery;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.statistics.UserExpensesStatistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ExpenseRepositoryTest {
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    UserRepository userRepository;
    Expense expense;
    List<Expense> expenses;
    User user;
    User user2;
    @BeforeEach
    public void saveExpense(){
        user = new User();
        user.setName("testUser");
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);

        user2 = new User();
        user2.setName("testUser2");
        user2.setUserId(UUID.randomUUID().toString());
        userRepository.save(user2);

        expenses = new ArrayList<>();
        for (int i = 0; i < 5 ;i++){
            expense = new Expense();
            expense.setUser(user);
            expense.setExpenseId(UUID.randomUUID().toString());
            expense.setItem("testItem");
            expense.setExpensePrice(0.1);
            expense.setAmount(5);
            expense.setTotalExpenseValue(expense.getAmount()*expense.getExpensePrice());
            expense.setExpenseDateTime(LocalDateTime.now());
            expenseRepository.save(expense);
            expenses.add(expense);
        }
    }

    @Test
    public void getTotalPurchaseValueForUserTest(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user);
        Double expectedValue = getTotalExpensePurchaseValue(expenses);
        assertTrue(totalPurchaseValueForUser.isPresent());
        assertNotNull(totalPurchaseValueForUser);
        assertEquals(expectedValue, totalPurchaseValueForUser.get());
    }

    public Double getTotalExpensePurchaseValue(List<Expense> expenses){
        return expenses.stream().mapToDouble(Expense::getTotalExpenseValue)
                .sum();
    }

    @Test
    public void getTotalPurchaseValueForUser2Test(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user2);
        assertFalse(totalPurchaseValueForUser.isPresent());
    }

    @Test
    public void getTotalPurchaseValueTest(){
        Optional<Double> totalPurchaseValue = expenseRepository.getTotalPurchaseValue();
        Double expectedValue = getTotalExpensePurchaseValue(expenses);
        assertTrue(totalPurchaseValue.isPresent());
        assertNotNull(totalPurchaseValue);
        assertEquals(expectedValue, totalPurchaseValue.get());
    }
    @Test
    public void getUserExpensesStatisticsTest(){
        List<UserExpensesStatistics> userExpensesStatistics = expenseRepository.getUserExpensesStatistics();
        assertFalse(userExpensesStatistics.isEmpty());
        assertEquals(user.getUserId(), userExpensesStatistics.get(0).getUserId());
        assertEquals(user.getName(), userExpensesStatistics.get(0).getUserName());
        Double expectedTotalValue = getTotalExpensePurchaseValue(expenses);
        assertEquals(expectedTotalValue, userExpensesStatistics.get(0).getTotalExpenseValue());
        assertEquals(expenses.size(), userExpensesStatistics.get(0).getHowManyExpenses());
    }

    @Test
    public void getExpenseDataQueryTest(){
        ExpenseDataQuery expenseDataQuery = expenseRepository.getExpenseDataQuery();
        assertNotNull(expenseDataQuery);
        assertEquals(getTotalExpensePurchaseValue(expenses), expenseDataQuery.getTotalExpensesValue());
        assertEquals(expenses.size(), expenseDataQuery.getHowManyExpenses());
    }
    @AfterEach
    public void deleteExpense(){
        expenseRepository.delete(expense);
        userRepository.delete(user);
    }
}
