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


@DataJpaTest
public class ExpenseRepositoryTest {
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    UserRepository userRepository;
    Expense expense;
    User user;
    User user2;
    User user3;
    @BeforeEach
    public void saveExpense(){
        user = new User();
        user.setName("testUser");
        user.setUserId("a");
        userRepository.save(user);

        user2 = new User();
        user2.setName("testUser2");
        user2.setUserId("b");
        userRepository.save(user2);

        user3 = new User();
        user3.setName("testUser3");
        user3.setUserId("c");
        userRepository.save(user3);

        for (int i = 1; i < 6 ;i++){
            expense = new Expense();
            expense.setUser(user);
            expense.setExpenseId("E"+i);
            expense.setItem("testItem");
            expense.setExpensePrice(1.0);
            expense.setAmount(5);
            expense.setTotalExpenseValue(expense.getAmount()*expense.getExpensePrice());
            expense.setExpenseDateTime(LocalDateTime.now());
            expenseRepository.save(expense);
        }

        expense = new Expense();
        expense.setUser(user3);
        expense.setExpenseId("E6");
        expense.setItem("testItem");
        expense.setExpensePrice(1.0);
        expense.setAmount(3);
        expense.setTotalExpenseValue(expense.getAmount()*expense.getExpensePrice());
        expense.setExpenseDateTime(LocalDateTime.now());
        expenseRepository.save(expense);

    }
    @Test
    public void getTotalPurchaseValueForUser3Test(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user3);
        assertTrue(totalPurchaseValueForUser.isPresent());
        assertNotNull(totalPurchaseValueForUser);
        assertEquals(3.0, totalPurchaseValueForUser.get());
    }

    @Test
    public void getTotalPurchaseValueForUserTest(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user);
        assertTrue(totalPurchaseValueForUser.isPresent());
        assertNotNull(totalPurchaseValueForUser);
        assertEquals(25.0, totalPurchaseValueForUser.get());
    }

    @Test
    public void getTotalPurchaseValueForUser2Test(){
        Optional<Double> totalPurchaseValueForUser = expenseRepository.getTotalPurchaseValueOfUser(user2);
        assertFalse(totalPurchaseValueForUser.isPresent());
    }

    @Test
    public void getTotalPurchaseValueTest(){
        Optional<Double> totalPurchaseValue = expenseRepository.getTotalPurchaseValue();
        assertTrue(totalPurchaseValue.isPresent());
        assertNotNull(totalPurchaseValue);
        assertEquals(28.0, totalPurchaseValue.get());
    }
    @Test
    public void getUserExpensesStatisticsTest(){
        List<UserExpensesStatistics> userExpensesStatistics = expenseRepository.getUserExpensesStatistics();
        assertFalse(userExpensesStatistics.isEmpty());
        assertEquals("a", userExpensesStatistics.get(0).getUserId());
        assertEquals("testUser", userExpensesStatistics.get(0).getUserName());
        assertEquals(25.0, userExpensesStatistics.get(0).getTotalExpenseValue());
        assertEquals(5, userExpensesStatistics.get(0).getHowManyExpenses());
    }

    @Test
    public void getExpenseDataQueryTest(){
        ExpenseDataQuery expenseDataQuery = expenseRepository.getExpenseDataQuery();
        assertNotNull(expenseDataQuery);
        assertEquals(28.0, expenseDataQuery.getTotalExpensesValue());
        assertEquals(6, expenseDataQuery.getHowManyExpenses());
    }
    @AfterEach
    public void deleteExpense(){
        expenseRepository.deleteAll();
        userRepository.deleteAll();
    }
}
