package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="USER_ID", nullable = false, length = 512)
    private String userId;
    @Column(name = "NAME", nullable = false, length = 512)
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<Order> orders = new ArrayList<>();
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
