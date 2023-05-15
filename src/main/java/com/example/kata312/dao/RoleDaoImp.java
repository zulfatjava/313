package com.example.kata312.dao;

import com.example.kata312.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> findAll() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }
    @Override
    public void createRole(Role role) {
        em.persist(role);
    }
    @Override
    public Role findRoleByAuthority(String authority) throws NoSuchElementException {
        return findAll().stream()
                .filter(r -> authority.equals(r.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Role %s not found", authority)));
    }

    @Override
    public Role getById(Long id) {
        return em.find(Role.class, id);
    }
}
