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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
        List<User> users = userRepository.getUserListById(userIDs);
        users.forEach(userRepository::delete);
    }
    @Transactional
    public void addOrder(String userId, String itemId, int amount, double purchasePrice, double sellPrice, boolean isSettled)
    throws UserNotFoundException, ItemNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new UserNotFoundException("user not found");
        Item item = itemRepository.findByItemId(itemId);
        if (item == null) throw new ItemNotFoundException("item not found");

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
    public void updateOrder(String originalOrderId, String newUserId, String itemId, Integer amount, Double newPurchasePrice, Double newSellPrice)
    throws OrderNotFoundException{
        User user = userRepository.findByUserId(newUserId);
        Item item = itemRepository.findByItemId(itemId);
        Order order = orderRepository.findByOrderId(originalOrderId);
        if (order == null) throw new OrderNotFoundException("order not found");
        order.setUser(user);
        order.setItem(item);
        order.setAmount(amount);
        order.setPurchasePrice(newPurchasePrice);
        order.setSellPrice(newSellPrice);
    }
    @Transactional
    public void settleTheOrder(String orderId) throws OrderNotFoundException{
       Order order = orderRepository.findByOrderId(orderId);
       if (order == null){
           LOG.info("Cannot find order with id {}", orderId);
           throw new OrderNotFoundException("order not found");
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
        List<Item> items = itemRepository.getItemListById(itemIDs);
        items.forEach(itemRepository::delete);
    }

    @Transactional
    public OrderData getOrder(String orderId) throws OrderNotFoundException{
        Order order = orderRepository.findByOrderId(orderId);
        if(order == null){
            LOG.info("order is null and the orderId is {}", orderId);
            throw new OrderNotFoundException("order not found");
        }else{
          return toDataService.convert(order);
        }
    }

    @Transactional
    public User getUserByName(String userName) throws UserNotFoundException {
        User user = userRepository.findByName(userName);
        if(user == null) throw new UserNotFoundException("user not found");
        return user;
    }
    @Transactional
    public Item getItemByName(String itemName) throws ItemNotFoundException{
        Item item = itemRepository.findByName(itemName);
        if (item == null) throw new ItemNotFoundException("item not found");
        return item;
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