package com.example.restsequrity.controller;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @PreAuthorize("permitAll()")
    public String permitAll()
    {
        return "all access";
    }

    @GetMapping("/user")
    //@PreAuthorize("hasAuthority(T(com.example.restsequrity.model.AppRole).USER)")
    @PreAuthorize("hasAuthority('USER')")
    public String onlyUsers(Principal principal)
    {

        int t = 0;

        return "only users";
    }

    @GetMapping("/root")
    @PreAuthorize("hasAuthority(T(com.example.restsequrity.model.AppRole).ROOT)")
    public String onlyRoot()
    {
        return "only root";
    }

}
