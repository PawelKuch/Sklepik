package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String itemId;
    @OneToMany(mappedBy = "item")
    private List<Order> order;

    public void setName(String name) {
        this.name = name;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }

    public String getItemId() {
        return itemId;
    }

}
