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
    User user;
    @BeforeEach
    public void saveExpense(){
        expense = new Expense();
        user = new User();
        user.setName("testUser");
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
        expense.setUser(user);
        expense.setExpenseId(UUID.randomUUID().toString());
        expense.setItem("testItem");
        expense.setExpensePrice(0.1);
        expense.setAmount(5);
        expense.setTotalExpenseValue(expense.getAmount()*expense.getExpensePrice());
        expense.setExpenseDateTime(LocalDateTime.now());
        expenseRepository.save(expense);

    }

    @Test
    public void getTotalPurchaseValueForUserTest(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user);
        Double expectedValue = expense.getTotalExpenseValue();
        assertTrue(totalPurchaseValueForUser.isPresent());
        assertNotNull(totalPurchaseValueForUser);
        assertEquals(expectedValue, totalPurchaseValueForUser.get());
    }
    @Test
    public void getTotalPurchaseValueTest(){
        Optional<Double> totalPurchaseValue = expenseRepository.getTotalPurchaseValue();
        Double expectedValue = expense.getTotalExpenseValue();
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
        Double expectedTotalValue = expense.getTotalExpenseValue();
        assertEquals(expectedTotalValue, userExpensesStatistics.get(0).getTotalExpenseValue());
        assertEquals(1, userExpensesStatistics.get(0).getHowManyExpenses());
    }

    @Test
    public void getExpenseDataQueryTest(){
        ExpenseDataQuery expenseDataQuery = expenseRepository.getExpenseDataQuery();
        assertNotNull(expenseDataQuery);
        assertEquals(expense.getTotalExpenseValue(), expenseDataQuery.getTotalExpensesValue());
        assertEquals(1, expenseDataQuery.getHowManyExpenses());
    }
    @AfterEach
    public void deleteExpense(){
        expenseRepository.delete(expense);
        userRepository.delete(user);
    }
}
