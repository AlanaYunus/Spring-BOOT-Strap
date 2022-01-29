package com.bootstrap.bootstrap.DAO;

import com.bootstrap.bootstrap.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    User createUser(User user);

    User readUser(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    User getUserByName(String name);

    List<User> allUsers();

    boolean isAllowed(Long id, Principal principal);
}
