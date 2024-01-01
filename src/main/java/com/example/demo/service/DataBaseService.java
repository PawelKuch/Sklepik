package com.example.demo.service;

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

    public DataBaseService(UserRepository userRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }
    @Transactional
    public void addUser(String name){
        User user = new User();
        user.setName(name);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }
    @Transactional
    public void addOrder(User user, Item item){
        if (user != null && item != null){
            Order order = new Order();
            order.setUser(user);
            order.setOrderId(UUID.randomUUID().toString());
            order.addProductToOrder(item);
            orderRepository.save(order);
        }
    }

    @Transactional
    public void addItem(String name) {
        Item item = new Item();
            item.setName(name);
            item.setItemId(UUID.randomUUID().toString());
            itemRepository.save(item);
    }

    public List<User> getUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }
    public User getUser(String userId){
        User user = userRepository.findByUserId(userId);
        return user;
    }

    public User getUserForOrder(Order order){
        List<Order> orders = (List<Order>) orderRepository.findAll();
        for (Order o : orders){
            if(o.getOrderId() == order.getOrderId()){
                User user = o.getUser();
                return user;
            }
        }
        return null;
    }
    public List<Order> getOrders(){
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return orders;
    }

    public Order getOrder(String orderId){
        Order order = orderRepository.findOrderByOrderId(orderId);
        return order;
    }

    public List<Item> getItems(){
        List<Item> items = itemRepository.findAll();
        return items;
    }
    public List<Item> getItemsForOrder(Order order){
        List<Item> items = itemRepository.findByOrder(order);
        return items;
    }
    public Item getItem(String productId){
        Item item = itemRepository.findByItemId(productId);
        return item;
    }
}
