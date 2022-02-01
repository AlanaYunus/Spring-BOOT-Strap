package com.bootstrap.bootstrap.controller;

import com.bootstrap.bootstrap.model.User;
import com.bootstrap.bootstrap.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class AdminController {

    private UserServiceImpl service;

    public AdminController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/admin/adminPanel")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", service.allUsers());
        model.addAttribute("thisUser", service.getUserByName(principal.getName()));
        model.addAttribute("newUser", new User());
        return "/admin/adminPanel";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        service.createUser(user);
        return "redirect:/admin/adminPanel";
    }

    @GetMapping("/userInfo/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.readUser(id));
        return "/admin/adminUpdate";
    }

    @PatchMapping("/userInfo/update")
    public String update(@ModelAttribute("user") User newUser) {
        service.updateUser(newUser);
        return "redirect:/admin/adminPanel";
    }

    @DeleteMapping("/userInfo/delete")
    public String delete(@ModelAttribute("user") User user) {
        service.deleteUser(user.getId());
        return "redirect:/admin/adminPanel";
    }

    @GetMapping("/admin/adminNew")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/adminNew";
    }
}
