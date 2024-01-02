package com.example.demo.service;

import com.example.demo.data.OwnerStatistics;
import com.example.demo.entity.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellingService {
    private List<Item> itemList = new ArrayList<>();
    private List<OwnerStatistics> ownerStatisticsList= new ArrayList<>();
    private double generalProfit;

    public void addItem(Item item){
        itemList.add(item);
    }
    public List<Item> getItemList(){
        return itemList;
    }
    public List<OwnerStatistics> calculateStatistics(){
        Map<String, Integer> ownerItemCount = new HashMap<>();
        Map<String, Double> ownerTotalSale = new HashMap<>();
        for (Item item : itemList){
            String owner = item.getName();
            int itemCount = ownerItemCount.getOrDefault(owner, 0);
            ownerItemCount.put(owner, itemCount + item.getAmount());
            double totalSale = ownerItemCount.getOrDefault(owner, 0);
            ownerTotalSale.put(owner, totalSale);
        }
        List<OwnerStatistics> ownerStatisticsList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : ownerItemCount.entrySet()){
            ownerStatisticsList.add(new OwnerStatistics(entry.getKey(),entry.getValue()));

        }
        return ownerStatisticsList;
    }


}
