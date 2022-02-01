package com.bootstrap.bootstrap.service;

import com.bootstrap.bootstrap.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    public void createUser(User user);

    public User readUser(Long id);

    public void updateUser(User user);

    public void deleteUser(Long id);

    public User getUserByName(String name);

    public List<User> allUsers();

    public boolean isAllowed(Long id, Principal principal);

}
