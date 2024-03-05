package com.example.demo.repository;
import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemId(String itemId);
    boolean existsByName(String itemName);
    Item findByName(String itemName);
}
