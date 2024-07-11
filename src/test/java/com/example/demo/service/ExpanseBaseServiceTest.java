package com.example.demo.service;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExpanseBaseServiceTest {
    @Mock
    ExpenseRepository expenseRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    ExpensesBaseService expensesBaseService;

    @Test
    public void addExpenseTest(){
        User user = new User();
        user.setUserId("user");

        Mockito.when(userRepository.findByUserId("user")).thenReturn(user);
        Assertions.assertDoesNotThrow( () ->
                expensesBaseService.addExpense("user", "item", 1,50.0));

        ArgumentCaptor<Expense> expenseCaptor = ArgumentCaptor.forClass(Expense.class);
        Mockito.verify(expenseRepository).save(expenseCaptor.capture());
        Expense capturedExpense = expenseCaptor.getValue();
        assertNotNull(capturedExpense);
        assertEquals("user", capturedExpense.getUser().getUserId());
        assertEquals("item", capturedExpense.getItem());
    }

    @Test
    public void addMissingUserExpense(){
        Mockito.when(userRepository.findByUserId("user")).thenReturn(null);

        Exception userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () ->
           expensesBaseService.addExpense("user", "item", 1,50.0));
        Mockito.verify(userRepository).findByUserId("user");
        assertNotNull(userNotFoundException);
        assertEquals("user not found", userNotFoundException.getMessage());
    }


}
