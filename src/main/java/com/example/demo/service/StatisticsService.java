package com.example.demo.service;

import com.example.demo.statistics.GeneralStatistics;
import com.example.demo.statistics.UserStatistics;
import com.example.demo.data.UserData;
import com.example.demo.entity.User;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StatisticsService {
    DataBaseService dataBaseService;
    OrderRepository orderRepository;
    ExpenseRepository expenseRepository;
    ItemRepository itemRepository;
    UserRepository userRepository;
    public StatisticsService(DataBaseService dataBaseService, OrderRepository orderRepository, ExpenseRepository expenseRepository,
                            ItemRepository itemRepository,UserRepository userRepository ){
        this.dataBaseService = dataBaseService;
        this.orderRepository = orderRepository;
        this.expenseRepository = expenseRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public GeneralStatistics getGeneralStatistics(){
        GeneralStatistics generalStatistics = new GeneralStatistics();
        Double totalOrderPurchaseValue = orderRepository.getTotalPurchaseValue().orElse(0.0);
        Double totalExpensePurchaseValue = expenseRepository.getTotalPurchaseValue().orElse(0.0);
        Double totalRevenue = orderRepository.getTotalRevenue().orElse(0.0);
        Double totalIncome = orderRepository.getTotalIncome().orElse(0.0);
        Long howManyOrders = orderRepository.count();
        Long howManyUsers = userRepository.count();
        Long howManyItems = itemRepository.count();
        Long howManySettledOrders = orderRepository.countByIsSettled(true);
        Long howManyUnsettledOrders = orderRepository.countByIsSettled(false);
        Long howManyExpenses = expenseRepository.count();

        generalStatistics.setTotalPurchaseValue(totalOrderPurchaseValue);
        generalStatistics.setTotalExpenseValue(totalExpensePurchaseValue);
        generalStatistics.setTotalRevenue(totalRevenue);
        generalStatistics.setTotalIncome(totalIncome);
        generalStatistics.setHowManyOrders(howManyOrders);
        generalStatistics.setHowManyUsers(howManyUsers);
        generalStatistics.setHowManyItems(howManyItems);
        generalStatistics.setHowManySettledOrders(howManySettledOrders);
        generalStatistics.setHowManyUnsettledOrders(howManyUnsettledOrders);
        generalStatistics.setHowManyExpenses(howManyExpenses);
        return generalStatistics;
    }

    @Transactional
    public UserStatistics getUserStatistics(UserData userData){
        UserStatistics statistics = new UserStatistics();
        User user = userRepository.findByUserId(userData.getUserId());
        Double totalPurchaseValue = expenseRepository.getTotalPurchaseValueOfUser(user).orElse(0.0);
        Double totalExpenseValue = expenseRepository.getTotalPurchaseValueOfUser(user).orElse(0.0);
        Double totalIncome = orderRepository.getTotalIncomeOfUser(user).orElse(0.0);
        Double totalRevenue = orderRepository.getTotalRevenueOfUser(user).orElse(0.0);
        Long howManyOrders = orderRepository.countByUser(user);
        Long howManySettledOrders = orderRepository.countByIsSettledAndUser(true, user);
        Long howManyUnsettledOrders = orderRepository.countByIsSettledAndUser(false, user);

        statistics.setUser(userData);
        statistics.setTotalPurchaseValue(totalPurchaseValue);
        statistics.setTotalExpenseValue(totalExpenseValue);
        statistics.setTotalIncomeValue(totalIncome);
        statistics.setTotalRevenueValue(totalRevenue);
        statistics.setHowManyOrders(howManyOrders);
        statistics.setHowManySettledOrders(howManySettledOrders);
        statistics.setHowManyUnsettledOrders(howManyUnsettledOrders);
        return statistics;
    }
    public List<UserStatistics> getUsersStatistics(){
        List<UserData> users = dataBaseService.getUsers();
        return users.stream().map(this::getUserStatistics).collect(Collectors.toList());
    }
}
