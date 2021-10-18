package com.example.capstoneproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/old/login")
    public String showLoginForm() {
        return "users/login";
    }
}
