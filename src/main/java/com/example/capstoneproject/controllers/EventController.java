package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import com.example.capstoneproject.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private final EventsRepository eventsDao;
    private final UsersRepository usersDao;
    private final EmailService emailService;

    public EventController(EventsRepository eventsDao, UsersRepository usersDao, EmailService emailService) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/events")
    public String showEvents(Model model) {
        List<Event> allEvents = eventsDao.findAll();
        model.addAttribute("events", allEvents);
        return "events/index";
    }

    @GetMapping("/events/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/events/create")
    public String createPost(@ModelAttribute Event eventToAdd
    ) {
        System.out.println(eventToAdd);
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventToAdd.setApi_id(1L);
        eventToAdd.setOwner(usersDao.getById(currentUserSession.getId()));

        emailService.prepareAndSend(
                eventToAdd,
                "new post",
                "You created a new post!"
        );

        eventsDao.save(eventToAdd);
        return "redirect:/events";
    }



}
