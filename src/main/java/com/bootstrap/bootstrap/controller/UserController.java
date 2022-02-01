package com.bootstrap.bootstrap.controller;

import com.bootstrap.bootstrap.model.User;
import com.bootstrap.bootstrap.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private UserServiceImpl service;

    public UserController(UserServiceImpl service) {
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

}