package com.example.kata312.service;

import com.example.kata312.model.Role;

import java.util.List;

public interface RoleService {

     List<Role> findAll();

    Role getById(Long id);
}

