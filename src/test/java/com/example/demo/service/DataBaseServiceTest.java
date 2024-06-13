package com.example.demo.service;

import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
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
    @Mock
    ToDataService toDataService;
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

    @Test
    public void settleTheOrderTest(){
        Order order1 = new Order();
        order1.setOrderId("order1");
        assertFalse(order1.isSettled());

        Mockito.when(orderRepository.findByOrderId("order1")).thenReturn(order1);
        dataBaseService.settleTheOrder("order1");
        assertTrue(order1.isSettled());
    }

    @Test
    public void removeItemsTest(){
        List<String> itemsIds = Arrays.asList("item1", "item2");
        Item item1 = new Item();
        item1.setItemId("item1");

        Item item2 = new Item();
        item2.setItemId("item2");

        Mockito.when(itemRepository.findByItemId("item1")).thenReturn(item1);
        Mockito.when(itemRepository.findByItemId("item2")).thenReturn(item2);

        ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);
        dataBaseService.removeItems(itemsIds);
        Mockito.verify(itemRepository, Mockito.times(2)).delete(itemCaptor.capture());
        List<Item> capturedItems = itemCaptor.getAllValues();
        assertEquals("item1", capturedItems.get(0).getItemId());
        assertEquals("item2", capturedItems.get(1).getItemId());
    }

    @Test
    public void getOrderTest(){
        Order order1 = new Order();
        order1.setOrderId("order1");

        OrderData orderData = new OrderData();
        orderData.setOrderId("order1");

        Mockito.when(orderRepository.findByOrderId("order1")).thenReturn(order1);
        Mockito.when(toDataService.convert(order1)).thenReturn(orderData);

        OrderData orderDataResult = dataBaseService.getOrder("order1");
        Mockito.verify(orderRepository).findByOrderId("order1");
        Mockito.verify(toDataService).convert(order1);

        assertNotNull(orderDataResult);
        assertEquals("order1", orderDataResult.getOrderId());
    }
    @Test
    public void getUserByNameTest(){
        User user1 = new User();
        user1.setName("user1");

        Mockito.when(userRepository.findByName("user1")).thenReturn(user1);
        User resultUser = dataBaseService.getUserByName("user1");
        Mockito.verify(userRepository).findByName("user1");
        assertNotNull(resultUser);
        assertEquals("user1", resultUser.getName());
    }

    @Test
    public void getItemByNameTest(){
        Item item1 = new Item();
        item1.setName("item1");

        Mockito.when(itemRepository.findByName("item1")).thenReturn(item1);
        Item itemResult = dataBaseService.getItemByName("item1");

        Mockito.verify(itemRepository).findByName("item1");
        assertNotNull(itemResult);
        assertEquals("item1", itemResult.getName());
    }

    @Test
    public void getUsersTest(){
        User user1 = new User();
        user1.setUserId("user1");

        User user2 = new User();
        user2.setUserId("user2");

        List<User> users = Arrays.asList(user1, user2);


        UserData userData1 = new UserData();
        userData1.setUserId("user1");

        UserData userData2 = new UserData();
        userData2.setUserId("user2");

        List<UserData> userDataList = Arrays.asList(userData1, userData2);

        Mockito.verify(userRepository).findAll();
        Mockito.verify(toDataService).getUsers(users);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(toDataService.getUsers(users)).thenReturn(userDataList);

        List<UserData> listResult = dataBaseService.getUsers();
        assertNotNull(listResult);
        assertEquals("user1", listResult.get(0).getUserId());
        assertEquals("user2", listResult.get(1).getUserId());
    }
}
