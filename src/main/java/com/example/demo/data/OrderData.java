package com.example.demo.data;

import jakarta.persistence.Column;

public class OrderData {
    private String orderId;
    private UserData user;
    private ItemData item;
    private int amount;
    private double purchasePrice;
    private double totalPurchaseValue;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public void setItem(ItemData item) {
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

    public UserData getUser() {
        return user;
    }

    public ItemData getItem() {
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
