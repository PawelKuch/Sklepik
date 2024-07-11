package com.example.demo.service;

import com.example.demo.data.OrderData;
import com.example.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ToDataServiceTest {

    @InjectMocks
    ToDataService toDataService;

    @Test
    public void getOrdersTest(){
        Order order1 = new Order();
        order1.setOrderId("order1");

        Order order2 = new Order();
        order2.setOrderId("order2");

        OrderData orderData1 = new OrderData();
        orderData1.setOrderId("order1");

        OrderData orderData2 = new OrderData();
        orderData2.setOrderId("order2");

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
