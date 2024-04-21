package com.example.demo.data;

public class CountOrdersQueryData {
    private String userId;
    private String name;
    private Double totalPurchaseValue;
    private Double totalExpenseValue;
    private Double totalRevenue;
    private Double totalIncome;
    private Long howManyOrders;


    public CountOrdersQueryData(String userId, String name, Double totalPurchaseValue,
                                Double totalExpenseValue, Double totalRevenue,
                                Double totalIncome, Long howManyOrders) {
        this.userId = userId;
        this.name = name;
        this.totalPurchaseValue = totalPurchaseValue;
        this.totalExpenseValue = totalExpenseValue;
        this.totalRevenue = totalRevenue;
        this.totalIncome = totalIncome;
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

    public void setTotalExpenseValue(Double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
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

    public Double getTotalExpenseValue() {
        return totalExpenseValue;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public Long getHowManyOrders() {
        return howManyOrders;
    }


}
