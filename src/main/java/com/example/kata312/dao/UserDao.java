package com.example.kata312.dao;



import com.example.kata312.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDao  {
    void saveUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    List<User> findAll();
    User getUserById(Long id);
    User findByUsername(String username);
    }

