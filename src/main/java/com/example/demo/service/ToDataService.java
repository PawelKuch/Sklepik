package com.example.demo.service;
import com.example.demo.data.ItemData;
import com.example.demo.data.OrderData;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDataService {

    public List<OrderData> getOrders(List<Order> orders){
        return orders.stream().map(this::convert).toList();
    }

    public List<UserData> getUsers(List <User> users) {
        return users.stream().map(this::convert).toList();
    }

    public List<ItemData> getItems(List<Item> items){
        return items.stream().map(this::convert).toList();
    }

    public OrderData convert(Order order){
        OrderData orderData = new OrderData();
        orderData.setOrderId(order.getOrderId());
        orderData.setAmount(order.getAmount());
        orderData.setPurchasePrice(order.getPurchasePrice());
        orderData.setTotalPurchaseValue(order.getTotalPurchaseValue());
        orderData.setSellPrice(order.getSellPrice());
        orderData.setRevenue(order.getRevenue());
        orderData.setIncome(order.getIncome());
        orderData.setUser(convert(order.getUser()));
        orderData.setItem(convert(order.getItem()));
        orderData.setOrderDateTime(order.getOrderDateTime());
        orderData.setExpense(order.getExpense());
        return orderData;
    }
    public ItemData convert(Item data){
       ItemData itemData = new ItemData();
       itemData.setItemId(data.getItemId());
       itemData.setName(data.getName());
       return itemData;
    }
    public UserData convert(User data){
        UserData userData = new UserData();
        userData.setUserId(data.getUserId());
        userData.setName(data.getName());
        return userData;
    }

}
