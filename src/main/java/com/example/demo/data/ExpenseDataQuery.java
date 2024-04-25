package com.example.demo.data;

import java.util.Optional;

public class ExpenseDataQuery {
    private Double totalExpensesValue;
    private Long howManyExpenses;

    public ExpenseDataQuery(Double totalExpensesValue, Long howManyExpenses) {
        if (totalExpensesValue != null){
            this.totalExpensesValue = totalExpensesValue;
        }else {
            this.totalExpensesValue = 0.0;
        }
        this.howManyExpenses = howManyExpenses;
    }

    public Double getTotalExpensesValue() {
        return totalExpensesValue;
    }

    public Long getHowManyExpenses() {
        return howManyExpenses;
    }

    public void setTotalExpensesValue(Double totalExpensesValue) {
        this.totalExpensesValue = totalExpensesValue;
    }

    public void setHowManyExpenses(Long howManyExpenses) {
        this.howManyExpenses = howManyExpenses;
    }
}
