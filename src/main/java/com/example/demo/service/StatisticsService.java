package com.example.demo.service;

import com.example.demo.data.ExpenseDataQuery;
import com.example.demo.statistics.GeneralStatistics;
import com.example.demo.statistics.UserExpensesStatistics;
import com.example.demo.statistics.UserOrdersStatistics;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
public class StatisticsService {
    DataBaseService dataBaseService;
    OrderRepository orderRepository;
    ExpenseRepository expenseRepository;
    ItemRepository itemRepository;
    UserRepository userRepository;
    public StatisticsService(OrderRepository orderRepository, ExpenseRepository expenseRepository,
                            ItemRepository itemRepository,UserRepository userRepository ){
        this.orderRepository = orderRepository;
        this.expenseRepository = expenseRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public GeneralStatistics getGeneralStatistics(){
        ExpenseDataQuery expenseDataQuery = expenseRepository.getExpenseDataQuery();
        GeneralStatistics generalStatistics = orderRepository.getGeneralStatistics();
        generalStatistics.setHowManyUsers(userRepository.count());
        generalStatistics.setHowManyItems(itemRepository.count());
        generalStatistics.setTotalExpenseValue(expenseDataQuery.getTotalExpensesValue());
        generalStatistics.setHowManyExpenses(expenseDataQuery.getHowManyExpenses());
        return generalStatistics;
    }
    @Transactional
    public List<UserOrdersStatistics> getUserOrdersStatistics(){
        return orderRepository.getUserOrdersStatistics();
    }

    @Transactional
    public List<UserExpensesStatistics> getUserExpensesStatistics(){
        return expenseRepository.getUserExpensesStatistics();
    }
}
