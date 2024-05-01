package com.example.demo.statistics;

public class UserExpensesStatistics {
    private String userId;
    private String userName;
    private Double totalExpenseValue;
    private Long howManyExpenses;

    public UserExpensesStatistics(String userId, String name, Double totalExpenseValue, Long howManyExpenses) {
        this.userId = userId;
        this.userName = name;
        this.totalExpenseValue = totalExpenseValue;
        this.howManyExpenses = howManyExpenses;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalExpenseValue(Double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }

    public void setHowManyExpenses(Long howManyExpenses) {
        this.howManyExpenses = howManyExpenses;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Double getTotalExpenseValue() {
        return totalExpenseValue;
    }

    public Long getHowManyExpenses() {
        return howManyExpenses;
    }
}
