package com.example.demo.service;

import com.example.demo.data.ExpenseData;
import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Expense;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@ExtendWith(MockitoExtension.class)
public class ToDataServiceTest {

    @InjectMocks
    ToDataService toDataService;

    @Mock
    Order order;
    @Mock
    User user;
    @Mock
    Expense expense;
    @Mock
    Item item;

    @Test
    public void convertUser(){
        Mockito.when(user.getUserId()).thenReturn("user1");
        Mockito.when(user.getName()).thenReturn("user1Name");

        UserData userDataResult = toDataService.convert(user);

        Assertions.assertNotNull(userDataResult);
        Assertions.assertEquals("user1",userDataResult.getUserId());
        Assertions.assertEquals("user1Name", userDataResult.getName());
    }

    @Test
    public void convertOrderTest(){
        User user1 = new User();
        user1.setUserId("user1");
        
        Item item1 = new Item();
        item1.setItemId("item1");
        
        Mockito.when(order.getOrderId()).thenReturn("order1");
        Mockito.when(order.getAmount()).thenReturn(100);
        Mockito.when(order.getPurchasePrice()).thenReturn(50.0);
        Mockito.when(order.getTotalPurchaseValue()).thenReturn(5000.0);
        Mockito.when(order.getSellPrice()).thenReturn(60.0);
        Mockito.when(order.getRevenue()).thenReturn(6000.0);
        Mockito.when(order.getIncome()).thenReturn(1000.0);
        LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 13, 12, 0);
        Mockito.when(order.getUser()).thenReturn(user1);
        Mockito.when(order.getItem()).thenReturn(item1);
        Mockito.when(order.getOrderDateTime()).thenReturn(localDateTime);
        Mockito.when(order.isSettled()).thenReturn(false);

        OrderData orderDataResult = toDataService.convert(order);

        Assertions.assertEquals("order1", orderDataResult.getOrderId());
        Assertions.assertEquals(100.0, orderDataResult.getAmount());
        Assertions.assertEquals(50.0, orderDataResult.getPurchasePrice());
        Assertions.assertEquals(60.0, orderDataResult.getSellPrice());
        Assertions.assertEquals(5000.0, orderDataResult.getTotalPurchaseValue());
        Assertions.assertEquals(6000.0, orderDataResult.getRevenue());
        Assertions.assertEquals(1000.0, orderDataResult.getIncome());
        Date dateResult = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        Assertions.assertEquals(dateResult, orderDataResult.getOrderDateTime());
        
        UserData userResult = orderDataResult.getUser();
        ItemData itemResult = orderDataResult.getItem();
        
        Assertions.assertNotNull(userResult);
        Assertions.assertNotNull(itemResult);
        Assertions.assertEquals("user1", userResult.getUserId());
        Assertions.assertEquals("item1", itemResult.getItemId());

        Assertions.assertFalse(orderDataResult.getIsSettled());
    }

    @Test
    public void convertExpenseTest(){
        User user1 = new User();
        user1.setUserId("user1");
        Mockito.when(expense.getExpenseId()).thenReturn("expense");
        Mockito.when(expense.getAmount()).thenReturn(10);
        Mockito.when(expense.getExpensePrice()).thenReturn(20.0);
        Mockito.when(expense.getTotalExpenseValue()).thenReturn(200.0);
        Mockito.when(expense.getUser()).thenReturn(user1);
        Mockito.when(expense.getItem()).thenReturn("item");
        LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 13, 12, 0);
        Mockito.when(expense.getExpenseDateTime()).thenReturn(localDateTime);

        ExpenseData expenseDataResult = toDataService.convert(expense);

        Assertions.assertEquals("expense", expenseDataResult.getExpenseId());
        Assertions.assertEquals(10, expenseDataResult.getAmount());
        Assertions.assertEquals(20.0, expenseDataResult.getExpensePrice());
        Assertions.assertEquals(200.0, expenseDataResult.getTotalExpenseValue());

        Date dateResult = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        Assertions.assertEquals(dateResult, expenseDataResult.getExpenseDateTime());

        UserData userResult = expenseDataResult.getUser();
        Assertions.assertNotNull(userResult);
        Assertions.assertEquals("user1", userResult.getUserId());
    }

    @Test
    public void convertItemTest(){
       Mockito.when(item.getItemId()).thenReturn("itemId");
       Mockito.when(item.getName()).thenReturn("itemName");

       ItemData itemDataResult = toDataService.convert(item);

       Assertions.assertNotNull(itemDataResult);
       Assertions.assertEquals("itemId", itemDataResult.getItemId());
       Assertions.assertEquals("itemName", itemDataResult.getName());
    }
}
