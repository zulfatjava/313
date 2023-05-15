package com.example.kata312.service;


import com.example.kata312.dao.UserDao;
import com.example.kata312.model.Role;
import com.example.kata312.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final RoleService roleService;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(RoleService roleService, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Transactional
    @Override
    public void saveUser(User user, Long[] roles) {
        addRole(user, roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user, Long[] roles) {
        addRole(user, roles);
        if (!user.getPassword().equals(getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    private Set<Role> addRole(User user, Long[] roles) {
        Set<Role> set = new HashSet<>();
        for (Long role : roles) {
            set.add(roleService.getById(role));
        }
        user.setRoles(set);
        return set;
    }

}