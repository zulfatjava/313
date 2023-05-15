package com.example.kata312.dao;

import com.example.kata312.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDao {
    void createRole(Role role);

    List<Role> findAll();

   Role findRoleByAuthority(String authority);

    Role getById(Long id);

}
