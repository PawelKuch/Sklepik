package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NAME", nullable = false, length = 512)
    private String name;
    @Column(name = "ITEM_ID", nullable = false, length = 512)
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
