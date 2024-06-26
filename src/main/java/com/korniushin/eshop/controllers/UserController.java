package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.UserService;
import com.korniushin.eshop.model.entities.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String users(Model model) {
        List<User> users = userService.all();
        User user = new User();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "/users";
    }

    @PostMapping
    public String add(User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public String getEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id).get());
        model.addAttribute("users", userService.all());
        return "/users";
    }

    @PostMapping("/{id}")

    public String update(User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
