package com.example.demo.service;

import com.example.demo.data.OrderFromCSV;
import com.example.demo.data.UserData;
import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileHandlerService {
    DataBaseService dataBaseService;
    public FileHandlerService(DataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
    }
    public List<String> getLinesFromCSV(){
        List<String> lines = IOUtils.readLines(FileHandlerService.class.getResourceAsStream("/arkusz.csv"), StandardCharsets.UTF_8);
        lines.remove(0);
        return lines;
    }
    public OrderFromCSV getOrderFromCSV(String line){
        String[] cols = line.split(";");
        OrderFromCSV orderFromCSV = new OrderFromCSV();
        if(!cols[0].isEmpty() && !cols[1].isEmpty() && !cols[2].isEmpty() && !cols[3].isEmpty() && !cols[6].isEmpty()){
            orderFromCSV.setUserName(cols[0]);
            orderFromCSV.setItemName(cols[1]);
            orderFromCSV.setAmount(Integer.parseInt(cols[2]));
            orderFromCSV.setPurchasePrice(Double.parseDouble(cols[3]));
            orderFromCSV.setSellPrice(Double.parseDouble(cols[6]));
        }
        return orderFromCSV;
    }
    public List<OrderFromCSV> getOrdersFromCSV(){
        List<String> lines = getLinesFromCSV();
        List<OrderFromCSV> ordersFromCSV = new ArrayList<>();
        String[] cols;
        for (String line : lines){
            ordersFromCSV.add(getOrderFromCSV(line));
        }
        return ordersFromCSV;
    }
    public String getUserNameFromCSV(OrderFromCSV orderFromCSV){
        return orderFromCSV.getUserName();
    }
    public String getItemNameFromCVS(OrderFromCSV orderFromCSV){
        return orderFromCSV.getItemName();
    }
    public Set<String> convertToSet(List<String> list){
        return list.stream().collect(Collectors.toSet());
    }
    public Set<String> getUserNamesFromCSV(List<OrderFromCSV> ordersFromCSV){
        return convertToSet(ordersFromCSV.stream().map(this::getUserNameFromCSV).toList());
    }
    @PostConstruct
    public void addUsersFromFile(){
        List<String> lines = getLinesFromCSV();
        List<OrderFromCSV> ordersFromCSV = getOrdersFromCSV();
        Set<String> userNames = getUserNamesFromCSV(ordersFromCSV);
        for(String userName : userNames) {
            dataBaseService.addUser(userName);
        }
    }

    public List<Item> getItemsFromFile() throws IOException{
        List<Item> items = new ArrayList<>();
        String content = IOUtils.toString(FileHandlerService.class.getResourceAsStream("/arkusz.csv"));
        String[] lines = content.split("\\n"); //wiersze z pliku
        String[] cols; //wiersze podzielone na kolumny - czyli wartości komórek
        int headerLine = 0;
        for (String line : lines) {
            if(headerLine > 0){
                cols = line.split(";");
                if(!cols[1].isEmpty()){
                    Item item = new Item();
                    item.setItemId(UUID.randomUUID().toString());
                    item.setName(cols[1]);
                    items.add(item);
                }
            }
            headerLine++;
        }
        return items;
    }
    public List<Integer> getAmountFromFile() throws IOException{
        List<Integer> amounts = new ArrayList<>();
        String content = IOUtils.toString(FileHandlerService.class.getResourceAsStream("/arkusz.csv"));
        String[] lines = content.split("\\n");
        String[] cols;
        int headerLine = 0;
        for (String line : lines) {
            if(headerLine > 0){
                cols = line.split(";");
                if(!cols[2].isEmpty()){
                    amounts.add(Integer.parseInt(cols[2]));
                }
            }
            headerLine++;
        }
        return amounts;
    }
    public List<Double> getPurchasePricesFromFile() throws IOException{
        List<Double> purchasePrices = new ArrayList<>();
        String content = IOUtils.toString(FileHandlerService.class.getResourceAsStream("/arkusz.csv"));
        String[] lines = content.split("\\n");
        String[] cols;
        int headerLine = 0;
        for (String line : lines) {
            if(headerLine > 0){
                cols = line.split(";");
                if(!cols[3].isEmpty()){
                    purchasePrices.add(Double.parseDouble(cols[3]));
                }
            }
            headerLine++;
        }
        return purchasePrices;
    }
    public List<Double> getSellPricesFromFile() throws IOException {
        List<Double> sellPrices = new ArrayList<>();
        String content = IOUtils.toString(FileHandlerService.class.getResourceAsStream("/arkusz.csv"));
        String[] lines = content.split("\\n");
        String[] cols;
        int headerLine = 0;
        for (String line : lines) {
            if(headerLine > 0){
                cols = line.split(";");
                if(!cols[6].isEmpty()){
                   sellPrices.add(Double.parseDouble(cols[6]));
                }
            }
            headerLine++;
        }
        return sellPrices;
    }


}
