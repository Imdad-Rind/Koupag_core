package com.Koupag.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "web/dashboard")
public class DashboardController {
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
}
