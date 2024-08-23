package com.example.demo.service;

import com.example.demo.data.ExpenseData;
import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Expense;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@ExtendWith(MockitoExtension.class)
public class ToDataServiceTest {

    @InjectMocks
    ToDataService toDataService;

    @Test
    public void convertUser(){
        User user = new User();
        user.setName("userName");
        user.setUserId("user");

        UserData userDataResult = toDataService.convert(user);

        Assertions.assertNotNull(userDataResult);
        Assertions.assertEquals("user",userDataResult.getUserId());
        Assertions.assertEquals("userName", userDataResult.getName());
    }

    @Test
    public void convertOrderTest(){
        Order order = getOrder();

        OrderData orderDataResult = toDataService.convert(order);

        Assertions.assertEquals("order", orderDataResult.getOrderId());
        Assertions.assertEquals(100.0, orderDataResult.getAmount());
        Assertions.assertEquals(50.0, orderDataResult.getPurchasePrice());
        Assertions.assertEquals(60.0, orderDataResult.getSellPrice());
        Assertions.assertEquals(5000.0, orderDataResult.getTotalPurchaseValue());
        Assertions.assertEquals(6000.0, orderDataResult.getRevenue());
        Assertions.assertEquals(1000.0, orderDataResult.getIncome());
        
        UserData userResult = orderDataResult.getUser();
        ItemData itemResult = orderDataResult.getItem();
        
        Assertions.assertNotNull(userResult);
        Assertions.assertNotNull(itemResult);
        Assertions.assertEquals("user", userResult.getUserId());
        Assertions.assertEquals("item", itemResult.getItemId());
        Assertions.assertFalse(orderDataResult.getIsSettled());
    }

    private static Order getOrder() {
        User user = new User();
        user.setUserId("user");

        Item item = new Item();
        item.setItemId("item");

        Order order = new Order();
        order.setOrderId("order");
        order.setAmount(100);
        order.setPurchasePrice(50.0);
        order.setTotalPurchaseValue(5000.0);
        order.setSellPrice(60.0);
        order.setRevenue(6000.0);
        order.setIncome(1000.0);
        order.setOrderDateTime(LocalDateTime.of(2023, 8, 13, 12, 0));
        order.setUser(user);
        order.setItem(item);
        order.setSettled(false);
        return order;
    }

    @Test
    public void convertExpenseTest(){
        Expense expense = getExpense();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 13, 12, 0);
        Date dateResult = Date.from(localDateTime.toInstant(ZoneOffset.UTC));

        ExpenseData expenseDataResult = toDataService.convert(expense);

        Assertions.assertEquals("expense", expenseDataResult.getExpenseId());
        Assertions.assertEquals(10, expenseDataResult.getAmount());
        Assertions.assertEquals(20.0, expenseDataResult.getExpensePrice());
        Assertions.assertEquals(200.0, expenseDataResult.getTotalExpenseValue());
        Assertions.assertEquals(dateResult, expenseDataResult.getExpenseDateTime());

        UserData userResult = expenseDataResult.getUser();
        Assertions.assertNotNull(userResult);
        Assertions.assertEquals("user", userResult.getUserId());
    }

    private static Expense getExpense(){
        User user = new User();
        user.setUserId("user");

        Expense expense = new Expense();
        expense.setExpenseId("expense");
        expense.setAmount(10);
        expense.setExpensePrice(20.0);
        expense.setTotalExpenseValue(200.0);
        expense.setUser(user);
        expense.setItem("item");
        expense.setExpenseDateTime(LocalDateTime.of(2023, 8, 13, 12, 0));

        return expense;
    }

    @Test
    public void convertItemTest(){
       Item item = new Item();
       item.setName("itemName");
       item.setItemId("itemId");

       ItemData itemDataResult = toDataService.convert(item);

       Assertions.assertNotNull(itemDataResult);
       Assertions.assertEquals("itemId", itemDataResult.getItemId());
       Assertions.assertEquals("itemName", itemDataResult.getName());
    }
}
