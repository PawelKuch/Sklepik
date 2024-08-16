package com.example.demo.service;

import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ToDataServiceTest {

    @InjectMocks
    ToDataService toDataService;

    @Mock
    UserRepository userRepository;
    @Mock
    ItemRepository itemRepository;

    @Mock
    Order order1;
    @Mock
    User user1;

    @Test
    public void getOrdersTest(){
        //String userId, String itemId, int amount, double purchasePrice, double sellPrice, boolean isSettled
        user1.setUserId("user1");

        User user2 = new User();
        user2.setUserId("user2");

        Item item1 = new Item();
        item1.setItemId("item1");


        order1.setOrderId("order1");
        order1.setUser(user1);
        order1.setItem(item1);
        order1.setAmount(100);
        order1.setPurchasePrice(50);
        order1.setTotalPurchaseValue(5000);
        order1.setSellPrice(60);
        order1.setRevenue(6000);
        order1.setIncome(1000);
        order1.setOrderDateTime(LocalDateTime.of(2023, 8, 13, 12, 0));
        order1.setSettled(false);

        Order order2 = new Order();
        order2.setOrderId("order2");
        order2.setUser(user2);
        order2.setItem(item1);

        OrderData orderData1 = new OrderData();
        orderData1.setOrderId("order1");

        OrderData orderData2 = new OrderData();
        orderData2.setOrderId("order2");

        UserData userData1 = new UserData();
        userData1.setUserId("userData1");


        Mockito.when(userRepository.findByUserId("user1")).thenReturn(user1);
        Mockito.when(userRepository.findByUserId("user2")).thenReturn(user2);
        Mockito.when(itemRepository.findByItemId("item1")).thenReturn(item1);
        Mockito.when(order1.getOrderDateTime()).thenReturn(LocalDateTime.of(2023, 8, 13, 12, 0));
        Mockito.when(user1.getUserId()).thenReturn("user1");
        Mockito.when(user1.getName()).thenReturn("user1");
        Mockito.when(toDataService.convert(user1)).thenReturn(userData1);
        Mockito.when(toDataService.convert(order1)).thenReturn(orderData1);
        Mockito.when(toDataService.convert(order2)).thenReturn(orderData2);

        OrderData orderData1Result = toDataService.convert(order1);
        OrderData orderData2Result = toDataService.convert(order2);

        Mockito.verify(toDataService).convert(order1);
        Mockito.verify(toDataService).convert(order2);

        assertNotNull(orderData1Result);
        assertNotNull(orderData2Result);

        assertEquals("order1", orderData1Result.getOrderId());
        assertEquals("order2", orderData2Result.getOrderId());
    }

}
