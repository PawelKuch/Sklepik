package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileHandlerService {
    DataBaseService dataBaseService;
    public FileHandlerService(DataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
    }

    public List<User> getUsersFromFile() throws IOException{
        List<User> users = new ArrayList<>();
        List<String> names = new ArrayList<>();
        String content = IOUtils.toString(FileHandlerService.class.getResourceAsStream("/arkusz.csv"));
        String[] lines = content.split("\\n");
        String[] cols;
        int headerLine = 0;
        for (String line : lines) {
            if(headerLine > 0){
                cols = line.split(";");
                if(!cols[0].isEmpty()){
                    String name = cols[0];
                    if(names.size() < 1){
                        names.add(name);
                        User user = new User();
                        user.setUserId(UUID.randomUUID().toString());
                        user.setName(cols[0]);
                        users.add(user);
                    }else if(names.size() > 0) {
                        if(!names.contains(name)){
                            names.add(name);
                            User user = new User();
                            user.setUserId(UUID.randomUUID().toString());
                            user.setName(cols[0]);
                            users.add(user);
                        }
                    }
                }
            }
            headerLine++;
        }



        //InputStream inputStream = FileHandlerService.class.getResourceAsStream("/arkusz.csv");
        /*InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            for (CSVRecord csvRecord : csvParser){
                String name = csvRecord.get("Kto");
                names.add(name);
                if(names.size() > 0){
                    if(!names.contains(name)){
                        User user = new User();
                        user.setUserId(UUID.randomUUID().toString());
                        user.setName(name);
                        users.add(user);
                    }
                }
            }
            return users;*/
        return users;
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
