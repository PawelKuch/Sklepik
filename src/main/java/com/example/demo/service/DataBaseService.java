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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Service
public class DataBaseService {
    private static final Logger LOG = LoggerFactory.getLogger(DataBaseService.class);
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
    public void removeUsers(List<String> userIDs){
        userIDs.forEach(o -> userRepository.delete(userRepository.findByUserId(o)));
    }
    @Transactional
    public void addOrder(String userId, String itemId, int amount, double purchasePrice, double sellPrice, boolean isSettled){
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
        order.setSettled(isSettled);
        order.setOrderId(UUID.randomUUID().toString());
        order.setItem(item);
        order.setOrderDateTime(LocalDateTime.now());
        orderRepository.save(order);
    }
    @Transactional
    public void settleTheOrder(String orderId){
       Order order = orderRepository.findByOrderId(orderId);
       if (order == null){
           LOG.info("Cannot find order with id {}", orderId);
       }else {
           order.setSettled(true);
       }
    }

    @Transactional
    public void addItem(String name) {
        Item item = new Item();
        item.setName(name);
        item.setItemId(UUID.randomUUID().toString());
        itemRepository.save(item);
    }
    @Transactional
    public void removeItems(List<String> itemIDs){
        itemIDs.forEach(o -> itemRepository.delete(itemRepository.findByItemId(o)));
    }

    @Transactional
    public User getUserByName(String userName){
        return userRepository.findByName(userName);
    }
    @Transactional
    public Item getItemByName(String itemName){
        return itemRepository.findByName(itemName);
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
    @Transactional
    public boolean userExists(String userName){
        return userRepository.existsByName(userName);
    }
    @Transactional
    public boolean itemExists(String itemName){
        return itemRepository.existsByName(itemName);
    }
}