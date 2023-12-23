package com.example.authdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }



    @GetMapping("/exit")
    public String logoutGet(Model model)
    {
        return "logout";
    }
    @PostMapping("/logout")
    public String logout(){
        return "redirect:/auth/login";
    }



}
