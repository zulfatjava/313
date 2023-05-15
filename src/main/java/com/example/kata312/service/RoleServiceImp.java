package com.example.kata312.service;

import com.example.kata312.dao.RoleDao;
import com.example.kata312.model.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

}

