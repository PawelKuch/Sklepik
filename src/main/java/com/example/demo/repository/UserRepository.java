package com.example.demo.repository;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    boolean existsByName(String userName);
    User findByName(String userName);

    @Query("SELECT u FROM User u WHERE u.userId IN ?1")
    List<User> getUserListById(List<String> userIds);

}
