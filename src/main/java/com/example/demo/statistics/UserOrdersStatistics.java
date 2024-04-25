package com.example.demo.statistics;


public class UserOrdersStatistics {
    private String userId;
    private String name;
    private Double totalPurchaseValue;
    private Double totalIncomeValue;
    private Double totalRevenueValue;
    private Long howManyOrders;

    public UserOrdersStatistics(String userId, String name, Double totalPurchaseValue,
                                Double totalRevenueValue, Double totalIncomeValue, Long howManyOrders) {
        this.userId = userId;
        this.name = name;
        this.totalPurchaseValue = totalPurchaseValue;
        this.totalIncomeValue = totalIncomeValue;
        this.totalRevenueValue = totalRevenueValue;
        this.howManyOrders = howManyOrders;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPurchaseValue(Double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
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

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Double getTotalPurchaseValue() {
        return totalPurchaseValue;
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

}
