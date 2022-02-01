package com.bootstrap.bootstrap.service;

import com.bootstrap.bootstrap.DAO.RoleDao;
import com.bootstrap.bootstrap.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Transactional
    public void saveRole(Role role) { roleDao.saveRole(role);}

    @Transactional
    public Role getRole(Long id) {return roleDao.getRole(id);}


    public Set<Role> setRole(Long index) {
        return roleDao.setRole(index);
    }

}