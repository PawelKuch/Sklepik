package com.example.demo.data;

public class OrderFromCSV {
    private String userName;
    private String itemName;
    private int amount;
    private double purchasePrice;
    private double sellPrice;
    private boolean isSettled;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public String getUserName() {
        return userName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public boolean isSettled() {
        return isSettled;
    }
}
