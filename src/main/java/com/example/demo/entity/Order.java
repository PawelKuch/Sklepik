package com.example.demo.entity;

import jakarta.persistence.*;



@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ORDER_ID", nullable = false, length = 512)
    private String orderId;
    @Column(name="AMOUNT", nullable=false, length=512)
    private int amount;
    @Column(name = "PURCHASE_PRICE", nullable = false, length = 512)
    private double purchasePrice;
    @Column(name = "TOTAL_PURCHASE_VALUE", nullable = false, length = 512)
    private double totalPurchaseValue;
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


}
