package com.example.capstoneproject.controllers;

import com.example.capstoneproject.repos.CommentsRepository;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventsDao;
    private CommentsRepository commentsDao;

    public SearchController(UsersRepository usersDao, PasswordEncoder passwordEncoder, EventsRepository eventsDao, CommentsRepository commentsDao) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.eventsDao = eventsDao;
        this.commentsDao = commentsDao;
    }


    @GetMapping( "/search")
    public String searchHome(@RequestParam(name="searchTerm")  String input,Model model ){
        model.addAttribute("input",input);
        return "users/search";
    }

}
