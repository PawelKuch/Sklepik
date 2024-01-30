package com.example.demo.service;

import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DataBaseService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private ToDataService toDataService;

    public DataBaseService(UserRepository userRepository, OrderRepository orderRepository, ItemRepository itemRepository,
                            ToDataService toDataService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.toDataService = toDataService;
    }
    @Transactional
    public void addUser(String name){
        User user = new User();
        user.setName(name);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }
    @Transactional
    public void addOrder(String userId, String itemId, int amount, double purchasePrice){
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new RuntimeException();

        Item item = itemRepository.findByItemId(itemId);
        if (item == null) throw new RuntimeException();

        Order order = new Order();
        order.setUser(user);
        order.setAmount(amount);
        order.setPurchasePrice(purchasePrice);
        order.setOrderId(UUID.randomUUID().toString());
        order.setItem(item);
        orderRepository.save(order);
    }


    @Transactional
    public void addItem(String name) {
        Item item = new Item();
        item.setName(name);
        item.setItemId(UUID.randomUUID().toString());
        itemRepository.save(item);
    }

    @Transactional
    public List<UserData> getUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        List<UserData> userDataList = toDataService.getUsers(users);
        return userDataList;
    }
    @Transactional
    public List<OrderData> getOrders(){
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<OrderData> orderDataList = toDataService.getOrders(orders);
        return orderDataList;
    }
    @Transactional
    public List<ItemData> getItems(){
        List<Item> items = (List<Item>) itemRepository.findAll();
        List<ItemData> itemDataList = toDataService.getItems(items);
        return itemDataList;
    }

}