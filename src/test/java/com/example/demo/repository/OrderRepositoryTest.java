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
    Order testOrder2;
    User testUser;
    User testUser2;
    User testUser3;
    Item testItem;
    Item testItem2;
    @BeforeEach
    public void saveOrder(){
        testUser = new User();
        testUser.setUserId("a");
        testUser.setName("testUser");
        userRepository.save(testUser);

        testUser2 = new User();
        testUser2.setUserId("b");
        testUser2.setName("testUser2");
        userRepository.save(testUser2);

        testUser3 = new User();
        testUser3.setUserId("c");
        testUser3.setName("testUser3");
        userRepository.save(testUser3);

        testItem = new Item();
        testItem.setItemId("1");
        testItem.setName("testItem");
        itemRepository.save(testItem);

        testItem2 = new Item();
        testItem2.setItemId("2");
        testItem2.setName("testItem2");
        itemRepository.save(testItem2);

        for (int i = 1; i < 6; i++){
            testOrder = new Order();
            testOrder.setOrderId("A" + i);
            testOrder.setUser(testUser);
            testOrder.setItem(testItem);
            testOrder.setAmount(2);
            testOrder.setPurchasePrice(2.5);
            testOrder.setSellPrice(3.0);
            testOrder.setTotalPurchaseValue(testOrder.getAmount() * testOrder.getPurchasePrice());
            testOrder.setRevenue(testOrder.getAmount() * testOrder.getSellPrice());
            testOrder.setIncome(testOrder.getRevenue() - testOrder.getTotalPurchaseValue());
            testOrder.setSettled(false);
            testOrder.setOrderDateTime(LocalDateTime.now());
            orderRepository.save(testOrder);
        }

        testOrder2 = new Order();
        testOrder2.setOrderId("A");
        testOrder2.setUser(testUser2);
        testOrder2.setItem(testItem2);
        testOrder2.setAmount(2);
        testOrder2.setPurchasePrice(2.0);
        testOrder2.setSellPrice(3.0);
        testOrder2.setTotalPurchaseValue(testOrder2.getAmount() * testOrder2.getPurchasePrice());
        testOrder2.setRevenue(testOrder2.getAmount() * testOrder2.getSellPrice());
        testOrder2.setIncome(testOrder2.getRevenue() - testOrder2.getTotalPurchaseValue());
        testOrder2.setSettled(false);
        testOrder2.setOrderDateTime(LocalDateTime.now());
        orderRepository.save(testOrder2);
    }

    @Test
    public void findByOrderIdTest(){
        Order order = orderRepository.findByOrderId("A1");
        assertNotNull(order);
        assertEquals("A1", order.getOrderId());
        assertEquals(2, order.getAmount());
        assertEquals(2.5, order.getPurchasePrice());
        assertEquals(5.0, order.getTotalPurchaseValue());
        assertEquals(3.0, order.getSellPrice());
        assertEquals(6, order.getRevenue());
        assertEquals(1, order.getIncome());
        assertFalse(order.isSettled());
    }

    @Test
    public void getUserOrdersStatisticsTest(){
        List<UserOrdersStatistics> userOrdersStatistics = orderRepository.getUserOrdersStatistics();
        assertFalse(userOrdersStatistics.isEmpty());
        assertEquals("a", userOrdersStatistics.get(0).getUserId());
        assertEquals("testUser", userOrdersStatistics.get(0).getName());
        assertEquals(25.0, userOrdersStatistics.get(0).getTotalPurchaseValue());
        assertEquals(30.0, userOrdersStatistics.get(0).getTotalRevenueValue());
        assertEquals(5.0, userOrdersStatistics.get(0).getTotalIncomeValue());
        assertEquals(5, userOrdersStatistics.get(0).getHowManyOrders());
    }

    @Test
    public void getGeneralStatisticsTest(){
        GeneralStatistics generalStatistics = orderRepository.getGeneralStatistics();
        assertNotNull(generalStatistics);
        assertEquals(29.0, generalStatistics.getTotalPurchaseValue());
        assertEquals(36.0, generalStatistics.getTotalRevenue());
        assertEquals(7.0, generalStatistics.getTotalIncome());
        assertEquals(6, generalStatistics.getHowManyOrders());
        assertNull(generalStatistics.getHowManyItems());
        assertNull(generalStatistics.getHowManyUsers());
    }

    @AfterEach
    public void deleteOrder(){
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

}
