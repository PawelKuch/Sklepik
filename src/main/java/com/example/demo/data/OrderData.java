package com.example.demo.data;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;

public class OrderData {
    private String orderId;
    private User user;
    private Item item;
    private int amount;
    private double purchasePrice;
    private double totalPurchaseValue;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setTotalPurchaseValue(double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    public double getTotalPurchaseValue() {
        return totalPurchaseValue;
    }
}
