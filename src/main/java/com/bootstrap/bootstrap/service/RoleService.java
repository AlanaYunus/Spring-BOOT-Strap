package com.bootstrap.bootstrap.service;

import com.bootstrap.bootstrap.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public List<Role> allRoles();

    public void saveRole(Role role);

    public Role getRole(Long id);

    public Set<Role> setRole(Long index);

}

