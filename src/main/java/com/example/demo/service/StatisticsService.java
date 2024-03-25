package com.example.demo.service;

import com.example.demo.data.UserStatistics;
import com.example.demo.data.UserData;
import com.example.demo.entity.User;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StatisticsService {
    private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);
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
    public Double getTotalPurchaseValue(){
        return orderRepository.getTotalPurchaseValue();
    }
    @Transactional
    public Double getTotalExpenseValue() {
        return expenseRepository.getTotalPurchaseValue();
    }
    public Double getTotalPurchaseAndExpenseValue(){
        return getTotalPurchaseValue() + getTotalExpenseValue();
    }
    @Transactional
    public Double getTotalIncome(){
        return orderRepository.getTotalIncome();
    }
    @Transactional
    public Double getTotalRevenue(){
        return orderRepository.getTotalRevenue();
    }
    @Transactional
    public Long getHowManyOrders(){
        return orderRepository.count();
    }
    @Transactional
    public Long getHowManyUsers(){
        return userRepository.count();
    }
    @Transactional
    public Long getHowManyItems(){
        return itemRepository.count();
    }
    @Transactional
    public int getHowManySettledOrders(){ return orderRepository.countByIsSettled(true);}
    @Transactional
    public int getHowManyUnsettledOrders(){return orderRepository.countByIsSettled(false); }
    private Double getUserTotalPurchaseValue(String userId){
        User user = dataBaseService.getUser(userId);
        return orderRepository.getTotalPurchaseValueOfUser(user);
    }
    private Double getUserTotalExpenseValue(String userId){
        User user = dataBaseService.getUser(userId);
        Double value = expenseRepository.getTotalPurchaseValueOfUser(user);
        if(value == null){
            LOG.info("Value is null for user: {}",userId );
        }
        return value;
    }
    private Double getTotalPurchaseAndExpenseValue(String userId){
        return getUserTotalPurchaseValue(userId)+getUserTotalExpenseValue(userId);
    }
    private Double getUserTotalIncome(String userId){
        return orderRepository.getTotalIncomeOfUser(dataBaseService.getUser(userId));
    }
    private Double getUserTotalRevenue(String userId){
        return orderRepository.getTotalRevenueOfUser(dataBaseService.getUser(userId));
    }
    private Integer getHowManyUserOrders(String userId){
        return orderRepository.countByUser(dataBaseService.getUser(userId));
    }
    private int getHowManyUserSettledOrders(String userId){
        return orderRepository.countByIsSettledAndUser(true, dataBaseService.getUser(userId));
    }
    private int getHowManyUserUnsettledOrders(String userId){
        return orderRepository.countByIsSettledAndUser(false, dataBaseService.getUser(userId));
    }
    @Transactional
    public UserStatistics getUserStatistics(UserData userData){
        UserStatistics statistics = new UserStatistics();
        statistics.setUser(userData);
        statistics.setTotalPurchaseValue(getUserTotalPurchaseValue(userData.getUserId()));
        statistics.setTotalExpenseValue(getUserTotalExpenseValue(userData.getUserId()));
        statistics.setTotalIncomeValue(getUserTotalIncome(userData.getUserId()));
        statistics.setTotalRevenueValue(getUserTotalRevenue(userData.getUserId()));
        statistics.setHowManyOrders(getHowManyUserOrders(userData.getUserId()));
        statistics.setHoManySettledOrders(getHowManyUserSettledOrders(userData.getUserId()));
        statistics.setHowManyUnsettledOrders(getHowManyUserUnsettledOrders(userData.getUserId()));
        return statistics;
    }
    public List<UserStatistics> getUsersStatistics(){
        List<UserData> users = dataBaseService.getUsers();
        return users.stream().map(this::getUserStatistics).collect(Collectors.toList());
    }
}
