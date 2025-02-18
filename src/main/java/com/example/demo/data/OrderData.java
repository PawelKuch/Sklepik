package com.example.demo.data;

import java.time.LocalDateTime;
import java.util.Date;


public class OrderData {
    private String orderId;
    private UserData user;
    private ItemData item;
    private int amount;
    private double purchasePrice;
    private double totalPurchaseValue;
    private double sellPrice;
    private double revenue;
    private double income;
    private Date orderDateTime;
    private boolean isSettled;
    private boolean isMultipack;

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

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getIncome() {
        return income;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }
    public boolean getIsSettled() {
        return isSettled;
    }

    public void setIsMultipack(boolean isMultipack){
        this.isMultipack = isMultipack;
    }
    public boolean getIsMultipack() {return isMultipack;}
}
