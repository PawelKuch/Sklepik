package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ORDER_ID", nullable = false, length = 512)
    private String orderId;
    @Column(name = "AMOUNT", nullable=false, length=512)
    private int amount;
    @Column(name = "PURCHASE_PRICE", nullable = false, length = 512)
    private double purchasePrice;
    @Column(name = "TOTAL_PURCHASE_VALUE", nullable = false, length = 512)
    private double totalPurchaseValue;
    @Column(name = "SELL_PRICE", length = 512)
    private double sellPrice;
    @Column(name = "REVENUE", length = 512)
    private double revenue;
    @Column(name = "INCOME", length = 512)
    private double income;
    @Column(name = "DATE", nullable = false, length = 512)
    private LocalDateTime orderDateTime;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public void setTotalPurchaseValue(double totalPurchaseValue) {
        this.totalPurchaseValue = totalPurchaseValue;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getTotalPurchaseValue() {
        return totalPurchaseValue;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
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

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getIncome() {
        return income;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

}
