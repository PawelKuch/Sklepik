package com.example.demo.data;

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
    private String orderDateTime;
    private boolean isExpense;

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

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }
    public void setExpense(boolean expense) {
        isExpense = expense;
    }
    public boolean getIsExpense() {
        return isExpense;
    }
}
