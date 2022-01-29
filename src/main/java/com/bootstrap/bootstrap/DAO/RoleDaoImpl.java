package com.bootstrap.bootstrap.DAO;

import com.bootstrap.bootstrap.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void saveRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Set<Role> setRole(Long index) {
        Set<Role> rolesSet = new HashSet<>();
        if (index == 1) {
            rolesSet.add(getRole(1L));
        } else if (index == 2) {
            rolesSet.add(getRole(2L));
        } else if (index == 3) {
            rolesSet.add(getRole(1L));
            rolesSet.add(getRole(2L));
        }
        return rolesSet;
    }

}