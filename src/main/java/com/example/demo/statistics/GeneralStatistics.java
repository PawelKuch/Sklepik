package com.example.demo.statistics;

public class GeneralStatistics {
    private Double totalPurchaseValue;
    private Double totalExpenseValue;
    private Double totalIncome;
    private Double totalRevenue;
    private Long howManyOrders;
    private Long howManyUsers;
    private Long howManyItems;
    public Long howManySettledOrders;
    public Long howManyUnsettledOrders;
    public Long howManyExpenses;

    public void setTotalPurchaseValue(Double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    public void setTotalExpenseValue(Double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
    public void setTotalRevenue(Double totalRevenue){
        this.totalRevenue = totalRevenue;
    }
    public void setHowManyOrders(Long howManyOrders) {
        this.howManyOrders = howManyOrders;
    }

    public void setHowManyUsers(Long howManyUsers) {
        this.howManyUsers = howManyUsers;
    }

    public void setHowManyItems(Long howManyItems) {
        this.howManyItems = howManyItems;
    }

    public void setHowManySettledOrders(Long howManySettledOrders) {
        this.howManySettledOrders = howManySettledOrders;
    }

    public void setHowManyUnsettledOrders(Long howManyUnsettledOrders) {
        this.howManyUnsettledOrders = howManyUnsettledOrders;
    }

    public void setHowManyExpenses(Long howManyExpenses) {
        this.howManyExpenses = howManyExpenses;
    }

    public Double getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    public Double getTotalExpenseValue() {
        return totalExpenseValue;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }
    public Double getTotalRevenue(){
        return totalRevenue;
    }

    public Long getHowManyOrders() {
        return howManyOrders;
    }

    public Long getHowManyUsers() {
        return howManyUsers;
    }

    public Long getHowManyItems() {
        return howManyItems;
    }

    public Long getHowManySettledOrders() {
        return howManySettledOrders;
    }

    public Long getHowManyUnsettledOrders() {
        return howManyUnsettledOrders;
    }

    public Long getHowManyExpenses() {
        return howManyExpenses;
    }
}
