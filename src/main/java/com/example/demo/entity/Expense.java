package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EXPENSE_ID", nullable = false, length = 512)
    private String expenseId;
    @Column(name = "AMOUNT", nullable=false, length=512)
    private int amount;
    @Column(name = "PURCHASE_PRICE", nullable = false, length = 512)
    private double expensePrice;
    @Column(name = "TOTAL_PURCHASE_VALUE", nullable = false, length = 512)
    private double totalExpenseValue;
    @Column(name = "DATE", nullable = false, length = 512)
    private LocalDateTime expenseDateTime;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public void setTotalExpenseValue(double totalExpenseValue) {
        this.totalExpenseValue = totalExpenseValue;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setExpensePrice(double expensePrice) {
        this.expensePrice = expensePrice;
    }
    public double getExpensePrice() {
        return expensePrice;
    }
    public double getTotalExpenseValue() {
        return totalExpenseValue;
    }
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    public String getExpenseId() {
        return expenseId;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }
    public Item getItem(){
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void setExpenseDateTime(LocalDateTime expenseDateTime) {
        this.expenseDateTime = expenseDateTime;
    }
    public LocalDateTime getExpenseDateTime() {
        return expenseDateTime;
    }

}
