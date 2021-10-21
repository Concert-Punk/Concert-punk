package com.example.capstoneproject.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String loggedOut() {
        return "users/logout";
    }

}



