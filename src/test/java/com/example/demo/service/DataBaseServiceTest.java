package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DataBaseServiceTest {
    @InjectMocks DataBaseService dataBaseService;
    @Mock
    UserRepository userRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ItemRepository itemRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUserTest(){
        dataBaseService.addUser("Pawel");
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userCaptor.capture());
        assertEquals("Pawel", userCaptor.getValue().getName());
    }

    @Test
    public void removeUsersTest(){
        List<String> userIds = Arrays.asList("1", "2");
        User user1 = new User();
        user1.setUserId("1");

        User user2 = new User();
        user2.setUserId("2");

        Mockito.when(userRepository.findByUserId("1")).thenReturn(user1);
        Mockito.when(userRepository.findByUserId("2")).thenReturn(user2);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        dataBaseService.removeUsers(userIds);
        Mockito.verify(userRepository, Mockito.times(2)).delete(userCaptor.capture());

        List<User> capturedUsers = userCaptor.getAllValues();

        assertEquals("1", capturedUsers.get(0).getUserId());
        assertEquals("2", capturedUsers.get(1).getUserId());
    }

    @Test
    public void addOrderTest(){
        User user = new User();
        user.setUserId("user1");
        Item item = new Item();
        item.setItemId("item1");
        Mockito.when(userRepository.findByUserId("user1")).thenReturn(user);
        Mockito.when(itemRepository.findByItemId("item1")).thenReturn(item);


        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        dataBaseService.addOrder("user1", "item1", 1, 1.0, 2.0, false );

        Mockito.verify(orderRepository).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertEquals(user, capturedOrder.getUser());
        assertEquals(item, capturedOrder.getItem());
        assertEquals(1, capturedOrder.getAmount());
        assertEquals(1.0, capturedOrder.getPurchasePrice());
        assertEquals(2.0, capturedOrder.getSellPrice());
        assertFalse(capturedOrder.isSettled());
        assertNotNull(capturedOrder.getOrderId());
    }

    @Test
    public void updateOrderTest(){
        User user1 = new User();
        user1.setUserId("user1");

        Order order1 = new Order();
        order1.setOrderId("order1");

        Item item1 = new Item();
        item1.setItemId("item1");

        Mockito.when(userRepository.findByUserId("user1")).thenReturn(user1);
        Mockito.when(itemRepository.findByItemId("item1")).thenReturn(item1);
        Mockito.when(orderRepository.findByOrderId("order1")).thenReturn(order1);

        dataBaseService.updateOrder("order1", "user1", "item1", 1, 1.0, 2.0);

        assertEquals(user1, order1.getUser());
        assertEquals(item1, order1.getItem());
        assertEquals(1, order1.getAmount());
        assertEquals(1.0, order1.getPurchasePrice());
        assertEquals(2.0, order1.getSellPrice());
    }

}
