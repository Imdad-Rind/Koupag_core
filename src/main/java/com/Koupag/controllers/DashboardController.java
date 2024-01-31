package com.Koupag.controllers;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "web/dashboard")
public class DashboardController {

    private final AuthenticationService authenticationService;

    @Autowired
    public DashboardController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
    @GetMapping("/home")
    public String dashboard(){return "admin/dashboard";}

    @PostMapping("/login")
    public String loginProcess(@RequestBody LoginDTO loginDTO){
        authenticationService.loginUser(loginDTO);
        return "redirect:web/dashboard/home";
    }
}
