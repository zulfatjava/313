package com.example.kata312.service;



import com.example.kata312.model.User;

import java.util.List;

public interface UserService{
    User getUserById(Long id);

    List<User> findAll();

    void saveUser(User user, Long[] roles);

    void deleteUser(Long id);

    void updateUser(User user, Long[] roless);
}
