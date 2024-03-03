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
            orderFromCSV.setUserName(cols[0].trim());
            orderFromCSV.setItemName(cols[1].trim());
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

    public Set<String> getUserNamesFromCSV(List<OrderFromCSV> ordersFromCSV) {
        return ordersFromCSV.stream()
                .map(OrderFromCSV::getUserName)
                .collect(Collectors.toSet());
    }

    public Set<String> getItemNamesFromCSV(List<OrderFromCSV> ordersFromCSV) {
        return ordersFromCSV.stream()
                .map(OrderFromCSV::getItemName)
                .collect(Collectors.toSet());
    }

    public void addUser(String name) {
        dataBaseService.addUser(name);
    }

    public void addUsersFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> userNames = getUserNamesFromCSV(ordersFromCSV);
        userNames.forEach(this::addUser);
    }

    public void addItem(String name) {
        dataBaseService.addItem(name);
    }

    public void addItemsFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> itemNames = getItemNamesFromCSV(ordersFromCSV);
        itemNames.forEach(this::addItem);
    }

    public void addOrderToDataBase(OrderFromCSV orderFromCSV) {
        String userId = dataBaseService.getUserByName(orderFromCSV.getUserName()).getUserId();
        String itemId = dataBaseService.getItemByName(orderFromCSV.getItemName()).getItemId();
        dataBaseService.addOrder(userId, itemId, orderFromCSV.getAmount(), orderFromCSV.getPurchasePrice(), orderFromCSV.getSellPrice());
    }

    @PostConstruct
    public void addOrdersFromFile() {
        List<String> lines = getLinesFromCSV();
        List<OrderFromCSV> ordersFromCSV = getOrdersFromCSV(lines);
        addUsersFromFile(ordersFromCSV);
        addItemsFromFile(ordersFromCSV);
        ordersFromCSV.forEach(this::addOrderToDataBase);
    }
}