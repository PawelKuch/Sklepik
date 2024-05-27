package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    User testUser;
    @BeforeEach
    public void saveUser(){
        testUser = new User();
        testUser.setUserId("1");
        testUser.setName("testName");
        userRepository.save(testUser);
    }

    @Test
    public void findUserById(){
        User savedUser = userRepository.findByUserId("1");
        assertNotNull(savedUser);
        assertEquals("1", savedUser.getUserId());
        assertEquals("testName", savedUser.getName());
    }

    @Test
    public void existsUserByName(){
        assertTrue(userRepository.existsByName("testName"));
    }

    @Test
    public void findUserByName(){
        User savedUser = userRepository.findByName("testName");
        assertNotNull(savedUser);
        assertEquals("testName", savedUser.getName());
        assertEquals("1", savedUser.getUserId());
    }

    @AfterEach
    public void deleteUserById(){
        userRepository.delete(testUser);
    }

}
