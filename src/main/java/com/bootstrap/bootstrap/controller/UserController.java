package com.bootstrap.bootstrap.controller;

import com.bootstrap.bootstrap.model.User;
import com.bootstrap.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/userInfo/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
        if (!service.isAllowed(id, principal)) {
            model.addAttribute("user", service.getUserByName(principal.getName()));
        } else {
            model.addAttribute("user", service.readUser(id));
        }
        return "/userInfo";
    }

    @GetMapping("/userInfo")
    public void userInfo(Principal principal, Model model) {
        User user = service.getUserByName(principal.getName());
        model.addAttribute(user);
        show(user.getId(), model, principal);
    }

    @GetMapping("/admin/adminNew")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/adminNew";
    }

}