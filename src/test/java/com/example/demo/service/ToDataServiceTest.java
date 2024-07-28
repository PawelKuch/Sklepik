package com.example.demo.service;

import com.example.demo.data.OrderData;
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

    @Test
    public void getOrdersTest(){
        //String userId, String itemId, int amount, double purchasePrice, double sellPrice, boolean isSettled
        User user1 = new User();
        user1.setUserId("user1");

        User user2 = new User();
        user2.setUserId("user2");

        Item item1 = new Item();
        item1.setItemId("item1");

        Order order1 = new Order();
        order1.setOrderId("order1");
        order1.setUser(user1);
        order1.setItem(item1);


        Order order2 = new Order();
        order2.setOrderId("order2");
        order2.setUser(user2);
        order2.setItem(item1);

        OrderData orderData1 = new OrderData();
        orderData1.setOrderId("order1");

        OrderData orderData2 = new OrderData();
        orderData2.setOrderId("order2");

        Mockito.when(userRepository.findByUserId("user1")).thenReturn(user1);
        Mockito.when(userRepository.findByUserId("user2")).thenReturn(user2);
        Mockito.when(itemRepository.findByItemId("item1")).thenReturn(item1);
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
