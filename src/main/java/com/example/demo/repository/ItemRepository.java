package com.example.demo.repository;
import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemId(String itemId);
    boolean existsByName(String itemName);
    Item findByName(String itemName);

    @Query("SELECT i FROM Item i WHERE i.itemId IN ?1")
    List<Item> getItemListById(List<String> itemIds);
}
