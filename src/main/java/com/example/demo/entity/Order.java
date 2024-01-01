package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void addProductToOrder(Item product){
        if(product != null){items.add(product);
        }
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
