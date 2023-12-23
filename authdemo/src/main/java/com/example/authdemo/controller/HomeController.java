package com.example.authdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index(Model model, Principal principal)
    {
        String uname = principal != null ? principal.getName() : null;
        model.addAttribute("user", uname);
        return "success";
    }
}
