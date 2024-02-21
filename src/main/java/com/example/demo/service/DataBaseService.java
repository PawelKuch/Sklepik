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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class DataBaseService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ToDataService toDataService;

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
    public void addOrder(String userId, String itemId, int amount, double purchasePrice, double sellPrice, boolean isExpense){
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new RuntimeException();

        Item item = itemRepository.findByItemId(itemId);
        if (item == null) throw new RuntimeException();

        Order order = new Order();
        order.setUser(user);
        order.setAmount(amount);
        order.setPurchasePrice(purchasePrice);
        order.setTotalPurchaseValue(amount*purchasePrice);
        order.setSellPrice(sellPrice);
        if (sellPrice == 0){
            order.setRevenue(0);
            order.setIncome(0);
        } else {
            order.setRevenue(amount*sellPrice);
            order.setIncome(order.getRevenue() - order.getTotalPurchaseValue());
        }
        order.setOrderId(UUID.randomUUID().toString());
        order.setItem(item);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        order.setOrderDateTime(LocalDateTime.now().format(formatter));
        order.setExpense(isExpense);
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
        return toDataService.getUsers(userRepository.findAll());
    }
    @Transactional
    public List<OrderData> getOrders(){
        return toDataService.getOrders(orderRepository.findAll());
    }
    @Transactional
    public List<ItemData> getItems(){
        return toDataService.getItems(itemRepository.findAll());
    }

}