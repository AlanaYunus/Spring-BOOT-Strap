package com.bootstrap.bootstrap.DAO;

import com.bootstrap.bootstrap.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> allRoles();

    Role getRole(Long id);

    Set<Role> setRole(Long index);

    public void saveRole(Role role);
}
