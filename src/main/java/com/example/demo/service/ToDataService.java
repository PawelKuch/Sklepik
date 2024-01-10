package com.example.demo.service;

import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ToDataService {

    public List<OrderData> getOrders(List<Order> orders){
        List<OrderData> orderDataList = new ArrayList<>();
        for (Order order : orders){
            OrderData orderData = new OrderData();
            orderData.setAmount(order.getAmount());
            orderData.setOrderId(order.getOrderId());
            orderData.setUser(order.getUser());
            orderData.setPurchasePrice(order.getPurchasePrice());
            orderData.setTotalPurchaseValue(order.getTotalPurchaseValue());
            orderDataList.add(orderData);
        }
        return orderDataList;
    }

    public List<UserData> getUsers(List <User> users) {
        List<UserData> userDataList = new ArrayList<>();
        for (User user : users){
            UserData userData = new UserData();
            userData.setUserId(user.getUserId());
            userData.setName(user.getName());
            userDataList.add(userData);
        }
        return userDataList;
    }

    public List<ItemData> getItems(List<Item> items){
        List<ItemData> itemDataList = new ArrayList<>();
        for(Item item : items){
            ItemData itemData = new ItemData();
            itemData.setName(item.getName());
            itemData.setItemId(item.getItemId());
            itemDataList.add(itemData);
        }
        return itemDataList;
    }
}
