package com.example.demo.service;

import com.example.demo.data.OrderFromCSV;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FileHandlerService {
    private static final Logger LOG = LoggerFactory.getLogger(FileHandlerService.class);
    DataBaseService dataBaseService;

    public FileHandlerService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public List<String> getLinesFromCSV() {
        try(InputStream inputStream = FileHandlerService.class.getResourceAsStream("/arkusz.csv")){
            List<String> lines = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
            lines.remove(0);
            return lines;
        }catch(IOException e){
            LOG.warn("Cannot read arkusz.csv File", e);
            return Collections.emptyList();
        }
    }

    public OrderFromCSV getOrderFromCSV(String line) {
        String[] cols = line.split(";");
        OrderFromCSV orderFromCSV = new OrderFromCSV();
        if (!cols[0].isEmpty() && !cols[1].isEmpty() && !cols[2].isEmpty() && !cols[3].isEmpty() && !cols[6].isEmpty()) {
            orderFromCSV.setUserName(cols[0].trim());
            orderFromCSV.setItemName(cols[1].trim());
            orderFromCSV.setAmount(Integer.parseInt(cols[2]));
            orderFromCSV.setPurchasePrice(Double.parseDouble(cols[3]));
            if (cols[5].trim().equals("rozliczono")){
                orderFromCSV.setSettled(true);
            }else {
                orderFromCSV.setSettled(false);
            }
            orderFromCSV.setSellPrice(Double.parseDouble(cols[6]));
        }
        return orderFromCSV;
    }

    public List<OrderFromCSV> getOrdersFromCSV(List<String> lines) {
        return lines.stream().map(this::getOrderFromCSV).toList();
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

    public void addUsersFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> userNames = getUserNamesFromCSV(ordersFromCSV);
        userNames.forEach(o -> dataBaseService.addUser(o));
    }

    public void addItemsFromFile(List<OrderFromCSV> ordersFromCSV) {
        Set<String> itemNames = getItemNamesFromCSV(ordersFromCSV);
        itemNames.forEach(o -> dataBaseService.addItem(o));
    }

    public void addOrderToDataBase(OrderFromCSV orderFromCSV) {
        String userId = dataBaseService.getUserByName(orderFromCSV.getUserName()).getUserId();
        String itemId = dataBaseService.getItemByName(orderFromCSV.getItemName()).getItemId();
        dataBaseService.addOrder(userId, itemId, orderFromCSV.getAmount(), orderFromCSV.getPurchasePrice(), orderFromCSV.getSellPrice(), orderFromCSV.isSettled());
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