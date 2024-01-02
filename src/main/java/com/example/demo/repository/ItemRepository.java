package com.example.demo.repository;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByOrder(Order order);
    Item findByItemId(String itemId);
}
