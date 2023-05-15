package com.example.kata312.controller;


import com.example.kata312.model.Role;
import com.example.kata312.model.User;
import com.example.kata312.service.RoleService;
import com.example.kata312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping()
    public String indexPage() {
        return "index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/users";
    }

    @PostMapping("/user/usercreate")
    public String add(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleService.findAll();
        model.addAttribute("allRoles", roles);
        return "redirect:/user-create";
    }

    @PatchMapping("/users/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roless") Long[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.getUserById(id);
        return "redirect:/admin/users";
    }
}
