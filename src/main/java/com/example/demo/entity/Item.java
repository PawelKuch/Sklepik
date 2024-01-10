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

    public void setName(String name) {
        this.name = name;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

}
