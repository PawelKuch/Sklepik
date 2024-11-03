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
        item.setItemId("a");
        itemRepository.save(item);
    }

    @Test
    public void findByItemIdTest(){
        Item savedItem = itemRepository.findByItemId("a");
        assertNotNull(savedItem);
        assertEquals("a", savedItem.getItemId());
        assertEquals("testItem", savedItem.getName());
    }

    @Test
    public void findByItemIdNegativeTest(){
        Item savedItem = itemRepository.findByItemId("b");
        assertNull(savedItem);
    }

    @Test
    public void existsByNameTest(){
        boolean doesExist = itemRepository.existsByName("testItem");
        assertTrue(doesExist);
    }

    @Test
    public void existsByNameNegativeTest(){
        boolean doesExist = itemRepository.existsByName("b");
        assertFalse(doesExist);
    }

    @Test
    public void findByNameTest(){
        Item savedItem = itemRepository.findByName("testItem");
        assertNotNull(savedItem);
        assertEquals("testItem", savedItem.getName());
        assertEquals("a", savedItem.getItemId());
    }

    @Test
    public void findByNameNegativeTest(){
        Item savedItem = itemRepository.findByName("b");
        assertNull(savedItem);
    }


    @AfterEach
    public void deleteItem(){
        itemRepository.deleteAll();
    }
}
