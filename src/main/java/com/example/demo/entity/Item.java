package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String itemId;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    private double purchasePrice;
    private double salesPrice;
    private int amount;
    private double markUp;
    private double profit;

    public void setName(String name) {
        this.name = name;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getItemId() {
        return itemId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public double getMarkUp() {
        return markUp;
    }
    public double getProfit(){
        return profit;
    }

    public int getAmount() {
        return amount;
    }
}
