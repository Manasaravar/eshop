package com.korniushin.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/account")

public class AccountController {

    @GetMapping
    public String getAccountPage () {
        return "account";
    }


}
