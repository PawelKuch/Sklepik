package com.example.demo;

public class OwnerStatistics {
    private String owner;
    private int amount;
    private double profit;
    private double totalSale;

    public OwnerStatistics(String owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public int getAmount() {
        return amount;
    }

    public double getProfit() {
        return profit;
    }

    public double getTotalSale() {
        return totalSale;
    }
}
