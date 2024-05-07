package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    Item item;

    @BeforeEach
    public void saveItem(){
        item = new Item();
        item.setName("testItem");
        item.setItemId(UUID.randomUUID().toString());
        itemRepository.save(item);
    }

    @Test
    public void findByItemIdTest(){
        Item savedItem = itemRepository.findByItemId(item.getItemId());
        assertNotNull(savedItem);
        assertEquals(item.getItemId(), savedItem.getItemId());
        assertEquals(item.getName(), savedItem.getName());
    }

    @Test
    public void existsByNameTest(){
        boolean doesExist = itemRepository.existsByName(item.getName());
        assertTrue(doesExist);
    }

    @Test
    public void findByNameTest(){
        Item savedItem = itemRepository.findByName(item.getName());
        assertNotNull(savedItem);
        assertEquals(item.getName(), savedItem.getName());
        assertEquals(item.getItemId(), savedItem.getItemId());
    }


    @AfterEach
    public void deleteItem(){
        itemRepository.delete(item);
    }
}
