package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.EventsRepository;
import com.example.capstoneproject.repos.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestCoordinator {

    private EventsRepository eventsDao;
    private UsersRepository usersDao;

    public RestCoordinator(EventsRepository eventsDao, UsersRepository usersDao) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
    }

    @PostMapping(value="/saveEvent",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Event saveEvent(@RequestBody(required = false) Event event) {
        System.out.println(event);
        User currentUserSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.setOwner(currentUserSession);
        return eventsDao.save(event);
    }
}
