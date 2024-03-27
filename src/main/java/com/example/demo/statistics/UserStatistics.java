package com.example.demo.statistics;

import com.example.demo.data.UserData;

public class UserStatistics {
    private UserData user;
    private Double totalPurchaseValue;
    private Double totalExpenseValue;
    private Double totalPurchaseAndExpenseValue;
    private Double totalIncomeValue;
    private Double totalRevenueValue;
    private Long howManyOrders;
    private Long howManySettledOrders;
    private Long howManyUnsettledOrders;

    public void setUser(UserData user) {
        this.user = user;
    }

    public void setTotalPurchaseValue(Double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    public void setTotalExpenseValue(Double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }

    public void setTotalPurchaseAndExpenseValue(Double totalPurchaseAndExpenseValue) {
        this.totalPurchaseAndExpenseValue = totalPurchaseAndExpenseValue;
    }

    public void setTotalIncomeValue(Double totalIncomeValue) {
        this.totalIncomeValue = totalIncomeValue;
    }

    public void setTotalRevenueValue(Double totalRevenueValue) {
        this.totalRevenueValue = totalRevenueValue;
    }

    public void setHowManyOrders(Long howManyOrders) {
        this.howManyOrders = howManyOrders;
    }

    public void setHowManySettledOrders(Long howManySettledOrders) {
        this.howManySettledOrders = howManySettledOrders;
    }

    public void setHowManyUnsettledOrders(Long howManyUnsettledOrders) {
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

    public Double getTotalPurchaseAndExpenseValue() {
        return totalPurchaseAndExpenseValue;
    }

    public Double getTotalIncomeValue() {
        return totalIncomeValue;
    }

    public Double getTotalRevenueValue() {
        return totalRevenueValue;
    }

    public Long getHowManyOrders() {
        return howManyOrders;
    }

    public Long getHowManySettledOrders() {
        return howManySettledOrders;
    }

    public Long getHowManyUnsettledOrders() {
        return howManyUnsettledOrders;
    }
}
