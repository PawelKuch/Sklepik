package com.example.demo.service;

import com.example.demo.data.ItemData;
import com.example.demo.data.OrderFromCSV;
import com.example.demo.data.UserData;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileHandlerService {
    DataBaseService dataBaseService;

    public FileHandlerService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public List<String> getLinesFromCSV() {
        List<String> lines = IOUtils.readLines(FileHandlerService.class.getResourceAsStream("/arkusz.csv"), StandardCharsets.UTF_8);
        lines.remove(0);
        return lines;
    }

    public OrderFromCSV getOrderFromCSV(String line) {
        String[] cols = line.split(";");
        OrderFromCSV orderFromCSV = new OrderFromCSV();
        if (!cols[0].isEmpty() && !cols[1].isEmpty() && !cols[2].isEmpty() && !cols[3].isEmpty() && !cols[6].isEmpty()) {
            orderFromCSV.setUserName(cols[0]);
            orderFromCSV.setItemName(cols[1]);
            orderFromCSV.setAmount(Integer.parseInt(cols[2]));
            orderFromCSV.setPurchasePrice(Double.parseDouble(cols[3]));
            orderFromCSV.setSellPrice(Double.parseDouble(cols[6]));
        }
        return orderFromCSV;
    }

    public List<OrderFromCSV> getOrdersFromCSV(List<String> lines) {
        List<OrderFromCSV> ordersFromCSV = new ArrayList<>();
        for (String line : lines) {
            ordersFromCSV.add(getOrderFromCSV(line));
        }
        return ordersFromCSV;
    }

    public String getUserNameFromCSV(OrderFromCSV orderFromCSV) {
        return orderFromCSV.getUserName();
    }

    public Set<String> convertToSet(List<String> list) {
        return list.stream().collect(Collectors.toSet());
    }

    public Set<String> getUserNamesFromCSV(List<OrderFromCSV> ordersFromCSV) {
        return convertToSet(ordersFromCSV.stream().map(this::getUserNameFromCSV).toList());
    }

    public String getItemNameFromCSV(OrderFromCSV orderFromCSV) {
        return orderFromCSV.getItemName();
    }

    public Set<String> getItemNamesFromCSV(List<OrderFromCSV> ordersFromCSV) {
        return convertToSet(ordersFromCSV.stream().map(this::getItemNameFromCSV).toList());
    }

    public void addUser(String name) {
        dataBaseService.addUser(name);
    }

    public void addUsersFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> userNames = getUserNamesFromCSV(ordersFromCSV);
        userNames.stream().forEach(this::addUser);
    }

    public void addItem(String name) {
        dataBaseService.addItem(name);
    }

    public void addItemsFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> itemNames = getItemNamesFromCSV(ordersFromCSV);
        itemNames.stream().forEach(this::addItem);
    }

    public Integer getAmountFromFile(OrderFromCSV orderFromCSV) {
        return orderFromCSV.getAmount();
    }

    public UserData getUserDataForOrder(String userName) {
        List<UserData> users = dataBaseService.getUsers();
        UserData userData = new UserData();
        for (UserData user : users) {
            if (user.getName().equals(userName)) {
                userData = user;
            }
        }
        return userData;
    }

    public ItemData getItemDataForOrder(String itemName) {
        List<ItemData> items = dataBaseService.getItems();
        ItemData itemData = new ItemData();
        for (ItemData item : items) {
            if (item.getName().equals(itemName)) {
                itemData = item;
            }
        }
        return itemData;
    }

    public void addOrder(OrderFromCSV orderFromCSV) {
        String userId = getUserDataForOrder(orderFromCSV.getUserName()).getUserId();
        String itemId = getItemDataForOrder(orderFromCSV.getItemName()).getItemId();
        dataBaseService.addOrder(userId, itemId, orderFromCSV.getAmount(), orderFromCSV.getPurchasePrice(), orderFromCSV.getSellPrice(), false);
    }

    @PostConstruct
    public void addOrdersFromFile() {
        List<String> lines = getLinesFromCSV();
        List<OrderFromCSV> ordersFromCSV = getOrdersFromCSV(lines);
        addUsersFromFile(ordersFromCSV);
        addItemsFromFile(ordersFromCSV);
        ordersFromCSV.stream().forEach(this::addOrder);
    }
}
