package com.example.kata312.dao;

import com.example.kata312.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (getUserById(id) != null) {
            entityManager.remove(getUserById(id));
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        }


    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u left join fetch u.roles where u.username=:username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}