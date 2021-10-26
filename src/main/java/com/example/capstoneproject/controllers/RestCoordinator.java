package com.example.capstoneproject.controllers;

import com.example.capstoneproject.models.Event;
import com.example.capstoneproject.models.User;
import com.example.capstoneproject.repos.EventsRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCoordinator {

    private EventsRepository eventsDao;

    public RestCoordinator(EventsRepository eventsDao) {
        this.eventsDao = eventsDao;
    }

//    @PostMapping(value="/saveEvent",
//    produces = MediaType.APPLICATION_JSON_VALUE)
//    public Event saveEvent(@RequestParam(required = false) Long events_id) {
//        Event event = eventsDao.findOne(events_id);
//        return eventsDao.save(event);
//    }
}
