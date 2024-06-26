package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.UserService;
import com.korniushin.eshop.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
@Autowired
  private UserService userService;

    @GetMapping
    public String RegistrationPage () {
        return "/registration";
    }

    @PostMapping

    public String registration (Model model, String username, String password, String matchingPassword) {

        if(!password.equals(matchingPassword)) {
            model.addAttribute("error", "password");
            return "/registration";
        }
        User registrUser = new User(username,password);
        if (userService.save(registrUser) == null) {
            model.addAttribute("error", "username");
            return "/registration";
        }
        return "redirect:/";
    }

}
