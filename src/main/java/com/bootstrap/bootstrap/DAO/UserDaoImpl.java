package com.bootstrap.bootstrap.DAO;

import com.bootstrap.bootstrap.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl(RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleDao.setRole(user.getRoleInd()));
        user.setRoles(roleDao.setRole(user.getRoleInd()));
        entityManager.persist(user);
        return user;
    }

    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        User userOld = getUserById(user.getId());
        user.setRoles(roleDao.setRole(user.getRoleInd()));

        if (user.getPassword().equals(userOld.getPassword())) {
            entityManager.merge(user);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.name = :name", User.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public boolean isAllowed(Long id, Principal principal) {
        User user = getUserByName(principal.getName());
        return user.getId() == id || user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().contains("ADMIN"));
    }
}