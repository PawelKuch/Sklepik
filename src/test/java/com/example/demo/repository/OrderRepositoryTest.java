package com.example.demo.repository;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.statistics.GeneralStatistics;
import com.example.demo.statistics.UserOrdersStatistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpenseRepository expenseRepository;
    Order testOrder;
    User testUser;
    Item testItem;

    @BeforeEach
    public void saveOrder(){
        testUser = new User();
        testUser.setUserId(UUID.randomUUID().toString());
        testUser.setName("testUser");
        userRepository.save(testUser);

        testItem = new Item();
        testItem.setItemId(UUID.randomUUID().toString());
        testItem.setName("testItem");
        itemRepository.save(testItem);

        testOrder = new Order();
        testOrder.setOrderId(UUID.randomUUID().toString());
        testOrder.setUser(testUser);
        testOrder.setItem(testItem);
        testOrder.setAmount(2);
        testOrder.setPurchasePrice(2.5);
        testOrder.setTotalPurchaseValue(testOrder.getAmount() * testOrder.getPurchasePrice());
        testOrder.setRevenue(testOrder.getAmount() * testOrder.getSellPrice());
        testOrder.setIncome(testOrder.getRevenue() - testOrder.getTotalPurchaseValue());
        testOrder.setSettled(false);
        testOrder.setOrderDateTime(LocalDateTime.now());
        orderRepository.save(testOrder);
    }

    @Test
    public void findByOrderIdTest(){
        String expectedOrderId = testOrder.getOrderId();
        Order order = orderRepository.findByOrderId(expectedOrderId);
        assertNotNull(order);
        assertEquals(expectedOrderId, order.getOrderId());
        assertEquals(testOrder.getAmount(), order.getAmount());
        assertEquals(testOrder.getPurchasePrice(), order.getPurchasePrice());
        assertEquals(testOrder.getTotalPurchaseValue(), order.getTotalPurchaseValue());
        assertEquals(testOrder.getSellPrice(), order.getSellPrice());
        assertEquals(testOrder.getRevenue(), order.getRevenue());
        assertEquals(testOrder.getIncome(), order.getIncome());
        assertEquals(testOrder.getOrderDateTime(), order.getOrderDateTime());
        assertEquals(testOrder.isSettled(), order.isSettled());
    }

    @Test
    public void getUserOrdersStatisticsTest(){
        List<UserOrdersStatistics> userOrdersStatistics = orderRepository.getUserOrdersStatistics();
        assertFalse(userOrdersStatistics.isEmpty());
        assertEquals(testUser.getUserId(), userOrdersStatistics.get(0).getUserId());
        assertEquals(testUser.getName(), userOrdersStatistics.get(0).getName());
        assertEquals(testOrder.getTotalPurchaseValue(), userOrdersStatistics.get(0).getTotalPurchaseValue());
        assertEquals(testOrder.getIncome(), userOrdersStatistics.get(0).getTotalIncomeValue());
        assertEquals(testOrder.getRevenue(), userOrdersStatistics.get(0).getTotalRevenueValue());
        assertEquals(1, userOrdersStatistics.get(0).getHowManyOrders());
    }

    @Test
    public void getGeneralStatisticsTest(){
        GeneralStatistics generalStatistics = orderRepository.getGeneralStatistics();
        assertNotNull(generalStatistics);
        assertEquals(testOrder.getTotalPurchaseValue(), generalStatistics.getTotalPurchaseValue());
        assertEquals(testOrder.getIncome(), generalStatistics.getTotalIncome());
        assertEquals(testOrder.getRevenue(), generalStatistics.getTotalRevenue());
        assertEquals(1, generalStatistics.getHowManyOrders());
        assertEquals(1, generalStatistics.getHowManyUsers());
        assertEquals(1, generalStatistics.getHowManyItems());
    }

    @AfterEach
    public void deleteOrder(){
        orderRepository.delete(testOrder);
        itemRepository.delete(testItem);
        userRepository.delete(testUser);
    }

}
