package com.example.demo.service;

import com.example.demo.data.ExpenseData;
import com.example.demo.data.OrderData;
import com.example.demo.entity.Expense;
import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesBaseService {
    ItemRepository itemRepository;
    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    ToDataService toDataService;
    public ExpensesBaseService(ItemRepository itemRepository, UserRepository userRepository,
                               ExpenseRepository expenseRepository, ToDataService toDataService){
        this.expenseRepository = expenseRepository;
        this.toDataService = toDataService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public void addExpense(String userId, String itemId, int amount, double purchasePrice){
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new RuntimeException();

        Item item = itemRepository.findByItemId(itemId);
        if (item == null) throw new RuntimeException();

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(amount);
        expense.setExpensePrice(purchasePrice);
        expense.setTotalExpenseValue(amount*purchasePrice);
        expense.setExpenseId(UUID.randomUUID().toString());
        expense.setItem(item);
        expense.setExpenseDateTime(LocalDateTime.now());
        expenseRepository.save(expense);
    }

    public List<ExpenseData> getExpenses(){
        return toDataService.getExpenses(expenseRepository.findAll());
    }
}
