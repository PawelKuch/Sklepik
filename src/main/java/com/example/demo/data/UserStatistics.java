package com.example.demo.data;

public class UserStatistics {
    private UserData user;
    private Double totalPurchaseValue;
    private Double totalExpenseValue;
    private Double totalIncomeValue;
    private Double totalRevenueValue;
    private int howManyOrders;
    private int hoManySettledOrders;
    private int howManyUnsettledOrders;

    public void setUser(UserData user) {
        this.user = user;
    }

    public void setTotalPurchaseValue(Double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    public void setTotalExpenseValue(Double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }

    public void setTotalIncomeValue(Double totalIncomeValue) {
        this.totalIncomeValue = totalIncomeValue;
    }

    public void setTotalRevenueValue(Double totalRevenueValue) {
        this.totalRevenueValue = totalRevenueValue;
    }

    public void setHowManyOrders(int howManyOrders) {
        this.howManyOrders = howManyOrders;
    }

    public void setHoManySettledOrders(int hoManySettledOrders) {
        this.hoManySettledOrders = hoManySettledOrders;
    }

    public void setHowManyUnsettledOrders(int howManyUnsettledOrders) {
        this.howManyUnsettledOrders = howManyUnsettledOrders;
    }

    public UserData getUser() {
        return user;
    }

    public Double getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    public Double getTotalExpenseValue() {
        return totalExpenseValue;
    }

    public Double getTotalIncomeValue() {
        return totalIncomeValue;
    }

    public Double getTotalRevenueValue() {
        return totalRevenueValue;
    }

    public int getHowManyOrders() {
        return howManyOrders;
    }

    public int getHoManySettledOrders() {
        return hoManySettledOrders;
    }

    public int getHowManyUnsettledOrders() {
        return howManyUnsettledOrders;
    }
}
