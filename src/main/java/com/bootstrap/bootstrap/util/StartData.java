package com.bootstrap.bootstrap.util;

import com.bootstrap.bootstrap.model.Role;
import com.bootstrap.bootstrap.model.User;
import com.bootstrap.bootstrap.service.RoleServiceImpl;
import com.bootstrap.bootstrap.service.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class StartData {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    public StartData(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {

        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        User admin = new User();
        admin.setName("admin");
        admin.setSurname("petrov");
        admin.setAge(33);
        admin.setEmail("admin@mail.ru");
        admin.setPassword("1234");
        Set<Role> roleSetAdmin = new HashSet<>();
        roleSetAdmin.add(roleService.getRole(1L));
        roleSetAdmin.add(roleService.getRole(2L));
        admin.setRoles(roleSetAdmin);
        admin.setRoleInd(3L);

        userService.createUser(admin);

        User normalUser = new User();
        normalUser.setName("bob");
        normalUser.setSurname("makovsky");
        normalUser.setEmail("bob@mail.ru");
        normalUser.setPassword("1234");
        Set<Role> roleSetNormalUser = new HashSet<>();
        roleSetNormalUser.add(roleService.getRole(2L));
        normalUser.setRoles(roleSetAdmin);
        normalUser.setRoleInd(2L);

        userService.createUser(normalUser);
    }
}

