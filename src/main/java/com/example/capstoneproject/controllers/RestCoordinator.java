package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.EventsRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestCoordinator {

    private EventsRepository eventsDao;

    public RestCoordinator(EventsRepository eventsDao) {
        this.eventsDao = eventsDao;
    }

    @PostMapping(value="/saveEvent",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Event saveEvent(@RequestBody(required = false) Event event) {
        System.out.println(event);
//        Event event = eventsDao.getOne(events_id);
        return eventsDao.save(event);
    }
}
