package com.example.demo.entity;

import jakarta.persistence.*;



@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private int amount;
    private double totalPurchaseValue;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Item item;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void addProductToOrder(Item product){
        if(product != null){
            item = product;
        }
    }

    public void setTotalPurchaseValue(double purchaseValue) {
        totalPurchaseValue = amount*purchaseValue;
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
