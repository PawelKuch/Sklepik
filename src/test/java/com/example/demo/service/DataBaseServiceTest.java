package com.example.demo.service;

import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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

        List<User> users = Arrays.asList(user1, user2);
        Mockito.when(userRepository.getUserListById(userIds)).thenReturn(users);


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

        Assertions.assertDoesNotThrow( () -> dataBaseService.addOrder("user1", "item1", 1, 1.0, 2.0, false ));

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
    public void addOrderMissingUserTest(){
        Mockito.when(userRepository.findByUserId("user")).thenReturn(null);

        Exception userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () ->
                dataBaseService.addOrder("user", "item1", 1, 1.0, 2.0, false ));
        Mockito.verify(userRepository).findByUserId("user");

        List<Order> orders = orderRepository.findAll();
        assertTrue(orders.isEmpty());
        assertEquals("user not found", userNotFoundException.getMessage());
    }

    @Test
    public void addOrderMissingItemTest(){
        User user = new User();
        user.setUserId("user");

        Mockito.when(userRepository.findByUserId("user")).thenReturn(user);
        Mockito.when(itemRepository.findByItemId("item")).thenReturn(null);


        Exception itemNotFoundException = Assertions.assertThrows(ItemNotFoundException.class, () ->
            dataBaseService.addOrder("user", "item", 1, 0.2, 0.4, false));
        Mockito.verify(userRepository).findByUserId("user");
        Mockito.verify(itemRepository).findByItemId("item");

        List<Order> orders = orderRepository.findAll();
        assertTrue(orders.isEmpty());
        assertEquals("item not found",itemNotFoundException.getMessage());
    }

    @Test
    public void addOrderMissingUserAndItemTest(){
        Mockito.when(userRepository.findByUserId("user")).thenReturn(null);
        Mockito.when(itemRepository.findByItemId("item")).thenReturn(null);

        Exception userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () ->
                dataBaseService.addOrder("user", "item", 1, 0.2, 0.4, false));

        List<Order> orders = orderRepository.findAll();

        assertTrue(orders.isEmpty());
        assertEquals("user not found", userNotFoundException.getMessage());

        User user1 = new User();
        user1.setUserId("user1");

        Mockito.when(userRepository.findByUserId("user1")).thenReturn(user1);

        Exception itemNotFoundException = Assertions.assertThrows(ItemNotFoundException.class, () ->
                dataBaseService.addOrder("user1", "item", 1, 0.2, 0.4, false));

        orders = orderRepository.findAll();
        assertTrue(orders.isEmpty());
        assertEquals("item not found", itemNotFoundException.getMessage());

        Mockito.verify(userRepository).findByUserId("user");
        Mockito.verify(userRepository).findByUserId("user1");
        Mockito.verify(itemRepository).findByItemId("item");
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

        Assertions.assertDoesNotThrow( ()-> dataBaseService.updateOrder("order1", "user1", "item1", 1, 1.0, 2.0));

        assertEquals(user1, order1.getUser());
        assertEquals(item1, order1.getItem());
        assertEquals(1, order1.getAmount());
        assertEquals(1.0, order1.getPurchasePrice());
        assertEquals(2.0, order1.getSellPrice());
    }
    @Test
    public void updateMissingOrderTest(){
        Mockito.when(orderRepository.findByOrderId("order")).thenReturn(null);

        Exception orderNotFoundException = Assertions.assertThrows(OrderNotFoundException.class, () ->
                dataBaseService.updateOrder("order", "user1", "item1", 1, 1.0, 2.0));
        Mockito.verify(orderRepository).findByOrderId("order");

        assertEquals("order not found", orderNotFoundException.getMessage());
    }

    @Test
    public void settleTheOrderTest(){
        Order order1 = new Order();
        order1.setOrderId("order1");
        assertFalse(order1.isSettled());

        Mockito.when(orderRepository.findByOrderId("order1")).thenReturn(order1);
        Assertions.assertDoesNotThrow(()-> dataBaseService.settleTheOrder("order1"));
        assertTrue(order1.isSettled());
        Mockito.verify(orderRepository).findByOrderId("order1");
    }
    @Test
    public void settleTheMissingOrderTest(){
        Mockito.when(orderRepository.findByOrderId("order")).thenReturn(null);
        Exception ex = Assertions.assertThrows(Exception.class, () -> {
           dataBaseService.settleTheOrder("order");
            Mockito.verify(orderRepository.findByOrderId("order"));
        });
        assertEquals("order not found", ex.getMessage());
    }

    @Test
    public void removeItemsTest(){
        List<String> itemsIds = Arrays.asList("item1", "item2");
        Item item1 = new Item();
        item1.setItemId("item1");

        Item item2 = new Item();
        item2.setItemId("item2");

        List<Item> items = Arrays.asList(item1, item2);
        Mockito.when(itemRepository.getItemListById(itemsIds)).thenReturn(items);


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

        OrderData orderDataResult = Assertions.assertDoesNotThrow(() ->
             dataBaseService.getOrder("order1"));
        Mockito.verify(orderRepository).findByOrderId("order1");
        assertNotNull(orderDataResult);
        assertEquals("order1", orderDataResult.getOrderId());
        Mockito.verify(toDataService).convert(order1);
    }

    @Test
    public void getMissingOrderTest(){
        Mockito.when(orderRepository.findByOrderId("order")).thenReturn(null);

        Exception ex = Assertions.assertThrows(Exception.class, () -> {
            OrderData orderDataResult = dataBaseService.getOrder("order");
            Mockito.verify(orderRepository).findByOrderId("order");
            assertNull(orderDataResult);
        });
        assertNotNull(ex);
        assertEquals("order not found", ex.getMessage());
    }
    @Test
    public void getUserByNameTest(){
        User user1 = new User();
        user1.setName("user1");

        Mockito.when(userRepository.findByName("user1")).thenReturn(user1);
        User userResult = Assertions.assertDoesNotThrow( () ->
            dataBaseService.getUserByName("user1"));
        Mockito.verify(userRepository).findByName("user1");
        assertNotNull(userResult);
        assertEquals("user1", userResult.getName());
    }

    @Test
    public void getUserByMissingNameTest(){
        Mockito.when(userRepository.findByName("user")).thenReturn(null);
        Exception userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, ()->
                dataBaseService.getUserByName("user"));
        Mockito.verify(userRepository).findByName("user");
        assertEquals("user not found", userNotFoundException.getMessage());
    }

    @Test
    public void getItemByNameTest(){
        Item item1 = new Item();
        item1.setName("item1");

        Mockito.when(itemRepository.findByName("item1")).thenReturn(item1);
        Item itemResult = Assertions.assertDoesNotThrow(() ->
                dataBaseService.getItemByName("item1"));
        assertNotNull(itemResult);
        assertEquals("item1", itemResult.getName());
        Mockito.verify(itemRepository).findByName("item1");
    }

    @Test
    public void getItemByMissingName(){
        Mockito.when(itemRepository.findByName("item")).thenReturn(null);

        Exception ItemNotFoundException = Assertions.assertThrows(com.example.demo.exception.ItemNotFoundException.class, () ->
                dataBaseService.getItemByName("item"));
        Mockito.verify(itemRepository).findByName("item");
        assertEquals("item not found", ItemNotFoundException.getMessage());
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

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(toDataService.getUsers(users)).thenReturn(userDataList);

        List<UserData> resultList = dataBaseService.getUsers();

        Mockito.verify(userRepository).findAll();
        Mockito.verify(toDataService).getUsers(users);

        assertNotNull(resultList);
        assertEquals("user1", resultList.get(0).getUserId());
        assertEquals("user2", resultList.get(1).getUserId());
    }

    @Test
    public void getOrdersTest(){
        Order order1 = new Order();
        order1.setOrderId("order1");

        Order order2 = new Order();
        order2.setOrderId("order2");

        List<Order> orders = Arrays.asList(order1, order2);

        OrderData orderData1 = new OrderData();
        orderData1.setOrderId("order1");

        OrderData orderData2 = new OrderData();
        orderData2.setOrderId("order2");

        List<OrderData> orderDataList = Arrays.asList(orderData1, orderData2);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        Mockito.when(toDataService.getOrders(orders)).thenReturn(orderDataList);

        List<OrderData> resultList = dataBaseService.getOrders();

        Mockito.verify(orderRepository).findAll();
        Mockito.verify(toDataService).getOrders(orders);

        assertNotNull(resultList);
        assertEquals("order1", resultList.get(0).getOrderId());
        assertEquals("order2", resultList.get(1).getOrderId());
    }

    @Test
    public void getItemsTest(){
        Item item1 = new Item();
        item1.setItemId("item1");

        Item item2 = new Item();
        item2.setItemId("item2");

        List<Item> items = Arrays.asList(item1, item2);

        ItemData itemData1 = new ItemData();
        itemData1.setItemId("item1");

        ItemData itemData2 = new ItemData();
        itemData2.setItemId("item2");

        List<ItemData> itemDataList = Arrays.asList(itemData1, itemData2);

        Mockito.when(itemRepository.findAll()).thenReturn(items);
        Mockito.when(toDataService.getItems(items)).thenReturn(itemDataList);

        List<ItemData> resultList = dataBaseService.getItems();

        Mockito.verify(itemRepository).findAll();
        Mockito.verify(toDataService).getItems(items);

        assertNotNull(resultList);
        assertEquals("item1", resultList.get(0).getItemId());
        assertEquals("item2", resultList.get(1).getItemId());
    }
}
