package com.bootstrap.bootstrap.service;

import com.bootstrap.bootstrap.DAO.UserDao;
import com.bootstrap.bootstrap.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private UserDao userDao;

   public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public User readUser(Long id) {
        return userDao.readUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    public boolean isAllowed(Long id, Principal principal) {
        return userDao.isAllowed(id, principal);
    }

}

